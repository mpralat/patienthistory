package com.mpralat.patienthistory.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.mpralat.patienthistory.domain.IdProvider
import com.mpralat.patienthistory.domain.doctor.Doctor
import com.mpralat.patienthistory.domain.doctor.Specialty
import com.mpralat.patienthistory.domain.doctor.repository.DoctorRepository
import com.mpralat.patienthistory.graphql.exception.CustomGraphQLException
import org.springframework.stereotype.Component

@Component
class DoctorMutationResolver(
        val doctorRepository: DoctorRepository,
        val idProvider: IdProvider
) : GraphQLMutationResolver {
    fun newDoctor(firstName: String, lastName: String, specialties: List<Specialty>?): Doctor {
        val doctor = Doctor(
                id = idProvider.getId(),
                firstName = firstName,
                lastName = lastName,
                specialties = specialties ?: emptyList()
        )
        doctorRepository.save(doctor)
        return doctor
    }

    fun deleteDoctor(id: String): Boolean {
        if (!doctorRepository.existsById(id)) throw CustomGraphQLException("No doctor with id: $id")
        doctorRepository.deleteById(id)
        return true
    }

    fun updateDoctor(id: String, firstName: String?, lastName: String?): Doctor? {
        if (!doctorRepository.existsById(id)) throw CustomGraphQLException("No doctor with id: $id")
        return doctorRepository.updateDoctor(
                id,
                firstName,
                lastName
        )
    }

    fun addSpecialty(id: String, newSpecialties: List<Specialty>): Doctor? {
        if (!doctorRepository.existsById(id)) throw CustomGraphQLException("No doctor with id: $id")
        return doctorRepository.addSpecialties(
                id = id,
                newSpecialties = newSpecialties
        )
    }
}
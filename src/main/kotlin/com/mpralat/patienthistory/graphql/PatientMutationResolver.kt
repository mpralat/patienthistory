package com.mpralat.patienthistory.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.mpralat.patienthistory.domain.IdProvider
import com.mpralat.patienthistory.domain.patient.Patient
import com.mpralat.patienthistory.domain.patient.Sex
import com.mpralat.patienthistory.domain.patient.repository.PatientRepository
import org.springframework.stereotype.Component
import java.util.*


@Component
class PatientMutationResolver(
        val patientRepository: PatientRepository,
        val idProvider: IdProvider
) : GraphQLMutationResolver {
    fun newPatient(firstName: String, lastName: String, pesel: String, age: Int, sex: Sex): Patient {
        val patient = Patient(
                id = idProvider.getId(),
                firstName = firstName,
                lastName = lastName,
                pesel = pesel,
                age = age,
                sex = sex
        )
        patientRepository.save(patient)
        return patient
    }

    fun deletePatient(id: String): Boolean {
        return try {
            // if id is not in UUID format, think of it as PESEL number
            UUID.fromString(id)
            patientRepository.deleteById(id)
            true
        } catch (exception: IllegalArgumentException) {
            patientRepository.deleteByPesel(id)
            true
        }
    }

    fun updatePatient(id: String, firstName: String?, lastName: String?, age: Int?, sex: Sex?): Patient? {
        return try {
            // if id is not in UUID format, think of it as PESEL number
            UUID.fromString(id)
            patientRepository.updatePatient(
                    id,
                    firstName,
                    lastName,
                    age,
                    sex
            )
        } catch (exception: IllegalArgumentException) {
            patientRepository.updatePatientByPesel(
                    id,
                    firstName,
                    lastName,
                    age,
                    sex
            )
        }
    }
}
package com.mpralat.patienthistory.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.mpralat.patienthistory.domain.IdProvider
import com.mpralat.patienthistory.domain.patient.Patient
import com.mpralat.patienthistory.domain.patient.Sex
import com.mpralat.patienthistory.domain.patient.repository.PatientRepository
import com.mpralat.patienthistory.graphql.exception.CustomGraphQLException
import com.mpralat.patienthistory.infrastructure.utils.validateName
import com.mpralat.patienthistory.infrastructure.utils.validatePeselNumber
import org.springframework.stereotype.Component
import java.util.*


@Component
class PatientMutationResolver(
        val patientRepository: PatientRepository,
        val idProvider: IdProvider
) : GraphQLMutationResolver {
    fun newPatient(firstName: String, lastName: String, pesel: String, age: Int, sex: Sex): Patient {
        if (!validatePeselNumber(pesel)) throw CustomGraphQLException("PESEL number is not valid!")
        if (!validateName(firstName) || !validateName(lastName)) throw CustomGraphQLException("Names cannot contain illegal characters")
        if (age < 0 || age > 150) throw CustomGraphQLException("Age should be in range [0, 150]")

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
        if (patientRepository.findPatient(id) == null) throw CustomGraphQLException("No patient with id: $id")
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
        if (patientRepository.findPatient(id) == null) throw CustomGraphQLException("No patient with id: $id")
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
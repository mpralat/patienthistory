package com.mpralat.patienthistory.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.mpralat.patienthistory.domain.patient.Patient
import com.mpralat.patienthistory.domain.patient.repository.PatientRepository
import com.mpralat.patienthistory.graphql.exception.CustomGraphQLException
import org.springframework.stereotype.Component

@Component
class PatientQueryResolver(
        val patientRepository: PatientRepository) : GraphQLQueryResolver {
    fun patients(): List<Patient> = patientRepository.findAll()
    fun patient(id: String): Patient? {
        return patientRepository.findPatient(id) ?: throw CustomGraphQLException("No such patient with id: $id")
    }
}
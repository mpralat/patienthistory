package com.mpralat.patienthistory.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.mpralat.patienthistory.domain.patient.Patient
import com.mpralat.patienthistory.domain.patient.repository.PatientRepository
import org.springframework.stereotype.Component

@Component
class PatientQueryResolver(
        val patientRepository: PatientRepository) : GraphQLQueryResolver {
    fun patients(): List<Patient> = patientRepository.findAll()
    fun patient(id: String): Patient? {
        return try {
            patientRepository.findPatientById(id)
        } catch (exception: IllegalArgumentException) {
            patientRepository.findPatientByPesel(id)
        }
    }
}
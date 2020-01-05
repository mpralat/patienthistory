package com.mpralat.patienthistory.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.mpralat.patienthistory.domain.patient.repository.PatientRepository
import com.mpralat.patienthistory.domain.patienthistory.PatientHistoryEntry
import com.mpralat.patienthistory.domain.patienthistory.repository.PatientHistoryRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class PatientHistoryQueryResolver(
        val patientHistoryRepository: PatientHistoryRepository,
        val patientRepository: PatientRepository
) : GraphQLQueryResolver {
    fun patientHistory(patientId: String): List<PatientHistoryEntry> {
        return try {
            // if patientId is not in UUID format, think of it as PESEL number
            UUID.fromString(patientId)
            patientHistoryRepository.findAllByPatientId(patientId)
        } catch (exception: IllegalArgumentException) {
            // todo throw sth useful
            val patient = patientRepository.findPatientByPesel(patientId) ?: throw NoSuchFieldError()
            patientHistoryRepository.findAllByPesel(pesel = patient.pesel)
        }
    }
}
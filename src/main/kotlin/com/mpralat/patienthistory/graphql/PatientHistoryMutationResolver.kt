package com.mpralat.patienthistory.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.mpralat.patienthistory.domain.DateProvider
import com.mpralat.patienthistory.domain.IdProvider
import com.mpralat.patienthistory.domain.patient.Patient
import com.mpralat.patienthistory.domain.patient.repository.PatientRepository
import com.mpralat.patienthistory.domain.patienthistory.PatientHistoryEntry
import com.mpralat.patienthistory.domain.patienthistory.repository.PatientHistoryRepository
import org.springframework.stereotype.Component

@Component
class PatientHistoryMutationResolver(
        val patientHistoryRepository: PatientHistoryRepository,
        val patientRepository: PatientRepository,
        val dateProvider: DateProvider,
        val idProvider: IdProvider
) : GraphQLMutationResolver {
    fun addPatientHistoryEntry(
            patientId: String?,
            pesel: String?,
            problemDescription: String?,
            doctorId: String,
            prescriptionIds: List<String>?
    ) : PatientHistoryEntry {
        var patient: Patient? = null
        when {
            patientId == null && pesel == null -> throw NoSuchFieldError()
            patientId != null -> patient = patientRepository.findPatientById(patientId)
            pesel != null -> patient = patientRepository.findPatientByPesel(pesel)
        }
        if (patient == null) throw NoSuchFieldError()

        val createdAt = dateProvider.getDate()
        val patientHistoryEntry = PatientHistoryEntry(
                id = idProvider.getId(),
                patientId = patient.id,
                pesel = patient.pesel,
                createdAt = createdAt,
                modifiedAt = createdAt,
                problemDescription = problemDescription,
                doctorId = doctorId,
                prescriptionIds = prescriptionIds
        )
        patientHistoryRepository.save(patientHistoryEntry)
        return patientHistoryEntry
    }
}
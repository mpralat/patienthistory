package com.mpralat.patienthistory.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.mpralat.patienthistory.domain.DateProvider
import com.mpralat.patienthistory.domain.IdProvider
import com.mpralat.patienthistory.domain.doctor.repository.DoctorRepository
import com.mpralat.patienthistory.domain.patient.repository.PatientRepository
import com.mpralat.patienthistory.domain.patienthistory.PatientHistoryEntry
import com.mpralat.patienthistory.domain.patienthistory.repository.PatientHistoryRepository
import com.mpralat.patienthistory.graphql.exception.CustomGraphQLException
import org.springframework.stereotype.Component

@Component
class PatientHistoryMutationResolver(
        val patientHistoryRepository: PatientHistoryRepository,
        val patientRepository: PatientRepository,
        val doctorRepository: DoctorRepository,
        val dateProvider: DateProvider,
        val idProvider: IdProvider
) : GraphQLMutationResolver {
    fun addPatientHistoryEntry(
            patientId: String?,
            pesel: String?,
            problemDescription: String,
            doctorId: String,
            prescriptions: List<String>?
    ): PatientHistoryEntry {
        if (patientId == null && pesel == null) throw CustomGraphQLException("To get patient history, please provide either PESEL number or patient ID.")
        val patient = patientRepository.findPatient(patientId ?: pesel!!)
                ?: throw CustomGraphQLException("No such patient $patientId, pesel: $pesel")
        if (!doctorRepository.existsById(doctorId)) throw CustomGraphQLException("No such doctor $doctorId")

        val createdAt = dateProvider.getDate()
        val patientHistoryEntry = PatientHistoryEntry(
                id = idProvider.getId(),
                patientId = patient.id,
                pesel = patient.pesel,
                createdAt = createdAt,
                modifiedAt = createdAt,
                problemDescription = problemDescription,
                doctorId = doctorId,
                prescriptions = prescriptions
        )
        patientHistoryRepository.save(patientHistoryEntry)
        return patientHistoryEntry
    }

    fun deletePatientHistoryEntry(id: String): Boolean {
        if (!patientHistoryRepository.existsById(id)) throw CustomGraphQLException("No patient history entry with id: $id")
        patientHistoryRepository.deleteById(id)
        return true
    }

    fun updatePatientHistoryEntry(id: String, problemDescription: String?, prescriptions: List<String>?): PatientHistoryEntry? {
        if (!patientHistoryRepository.existsById(id)) throw CustomGraphQLException("No patient history entry with id: $id")
        val modified = dateProvider.getDate()
        return patientHistoryRepository.updatePatientHistoryEntry(id, modified, problemDescription, prescriptions)
    }
}
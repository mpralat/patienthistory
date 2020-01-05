package com.mpralat.patienthistory.domain.patienthistory.repository

import com.mpralat.patienthistory.domain.patienthistory.PatientHistoryEntry
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientHistoryRepository : MongoRepository<PatientHistoryEntry, String>, PatientHistoryCustomRepository {
    fun findAllByPatientId(patientId: String): List<PatientHistoryEntry>
    fun findAllByPesel(pesel: String): List<PatientHistoryEntry>
}
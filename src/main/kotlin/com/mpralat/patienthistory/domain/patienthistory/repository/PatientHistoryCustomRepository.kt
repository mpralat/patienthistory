package com.mpralat.patienthistory.domain.patienthistory.repository

import com.mpralat.patienthistory.domain.patienthistory.PatientHistoryEntry
import java.util.Date

interface PatientHistoryCustomRepository {
    fun updatePatientHistoryEntry(id: String, modifiedAt: Date, problemDescription: String?, prescriptions: List<String>?): PatientHistoryEntry?
}
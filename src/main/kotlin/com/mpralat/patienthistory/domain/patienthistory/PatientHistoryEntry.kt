package com.mpralat.patienthistory.domain.patienthistory

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document(collection= "patientHistoryEntries")
data class PatientHistoryEntry constructor(
        @Id val id: String,
        @Indexed(background = true) val patientId: String,
        @Indexed(background = true) val pesel: String,
        val createdAt: Date,
        val modifiedAt: Date,
        val problemDescription: String?,
        val doctorId: String,
        val prescriptions: List<String>?
)
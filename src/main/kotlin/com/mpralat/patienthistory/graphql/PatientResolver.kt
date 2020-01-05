package com.mpralat.patienthistory.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import com.mpralat.patienthistory.domain.doctor.repository.DoctorRepository
import com.mpralat.patienthistory.domain.patient.repository.PatientRepository
import com.mpralat.patienthistory.domain.patienthistory.PatientHistoryEntry
import org.springframework.stereotype.Component

@Component
class PatientResolver(
        val patientRepository: PatientRepository,
        val doctorRepository: DoctorRepository
) : GraphQLResolver<PatientHistoryEntry> {
    fun patient(patientHistoryEntry: PatientHistoryEntry) = patientRepository.findPatient(patientHistoryEntry.patientId)
    fun doctor(patientHistoryEntry: PatientHistoryEntry) = doctorRepository.findById(patientHistoryEntry.doctorId)
}
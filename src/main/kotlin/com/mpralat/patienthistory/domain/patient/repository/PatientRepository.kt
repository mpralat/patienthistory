package com.mpralat.patienthistory.domain.patient.repository

import com.mpralat.patienthistory.domain.patient.Patient
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository : MongoRepository<Patient, String>, PatientCustomRepository {
    fun deleteByPesel(pesel: String): List<Patient>
    fun findByPesel(pesel: String): Patient
}
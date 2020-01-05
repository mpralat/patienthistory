package com.mpralat.patienthistory.domain.patient.repository

import com.mpralat.patienthistory.domain.patient.Patient
import com.mpralat.patienthistory.domain.patient.Sex

interface PatientCustomRepository {
    fun updatePatient(id: String, firstName: String?, lastName: String?, age: Int?, sex: Sex?): Patient?
    fun updatePatientByPesel(pesel: String, firstName: String?, lastName: String?, age: Int?, sex: Sex?): Patient?
    fun findPatient(id: String): Patient?
}
package com.mpralat.patienthistory.domain.doctor.repository

import com.mpralat.patienthistory.domain.doctor.Doctor
import com.mpralat.patienthistory.domain.doctor.Specialty

interface DoctorCustomRepository {
    fun updateDoctor(id: String, firstName: String?, lastName: String?): Doctor?
    fun addSpecialties(id: String, newSpecialties: List<Specialty>): Doctor?
}
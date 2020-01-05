package com.mpralat.patienthistory.domain.doctor.repository

import com.mpralat.patienthistory.domain.doctor.Doctor
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DoctorRepository : MongoRepository<Doctor, String>, DoctorCustomRepository
package com.mpralat.patienthistory.graphql
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.mpralat.patienthistory.domain.doctor.Doctor
import com.mpralat.patienthistory.domain.doctor.Specialty
import com.mpralat.patienthistory.domain.doctor.repository.DoctorRepository
import org.springframework.stereotype.Component

@Component
class DoctorQueryResolver(
        val doctorRepository: DoctorRepository) : GraphQLQueryResolver {
    fun doctors(): List<Doctor> = doctorRepository.findAll()
    fun specialties(): List<Specialty> = Specialty.values().toList()
}
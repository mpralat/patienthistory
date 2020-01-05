package com.mpralat.patienthistory.domain.doctor.repository

import com.mpralat.patienthistory.domain.doctor.Doctor
import com.mpralat.patienthistory.domain.doctor.Specialty
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update

class DoctorCustomRepositoryImpl(
        private val mongoTemplate: MongoTemplate
) : DoctorCustomRepository {
    override fun updateDoctor(id: String, firstName: String?, lastName: String?): Doctor? {
        val update = Update()
        if (lastName != null) update.set("lastName", lastName)
        if (firstName != null) update.set("firstName", firstName)

        return mongoTemplate.findAndModify(
                Query().addCriteria(Criteria.where("id").`is`(id)),
                update,
                FindAndModifyOptions.options().returnNew(true).upsert(false),
                Doctor::class.java
        )
    }

    override fun addSpecialties(id: String, newSpecialties: List<Specialty>): Doctor? {
        val update = Update()
        for (spec in newSpecialties) {
            update.addToSet("specialties", spec)
        }
        return mongoTemplate.findAndModify(
                Query().addCriteria(Criteria.where("id").`is`(id)),
                update,
                FindAndModifyOptions.options().returnNew(true).upsert(false),
                Doctor::class.java
        )
    }
}
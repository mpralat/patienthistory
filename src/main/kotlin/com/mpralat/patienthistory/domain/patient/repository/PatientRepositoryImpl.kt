package com.mpralat.patienthistory.domain.patient.repository

import com.mpralat.patienthistory.domain.patient.Patient
import com.mpralat.patienthistory.domain.patient.Sex
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update

class PatientRepositoryImpl(
        private val mongoTemplate: MongoTemplate
) : PatientCustomRepository {
    override fun updatePatient(id: String, firstName: String?, lastName: String?, age: Int?, sex: Sex?): Patient? {
        return mongoTemplate.findAndModify(
                Query().addCriteria(Criteria.where("id").`is`(id)),
                prepareUpdate(firstName, lastName, age, sex),
                FindAndModifyOptions.options().returnNew(true).upsert(false),
                Patient::class.java
        )
    }

    override fun updatePatientByPesel(pesel: String, firstName: String?, lastName: String?, age: Int?, sex: Sex?): Patient? {
        return mongoTemplate.findAndModify(
                Query().addCriteria(Criteria.where("pesel").`is`(pesel)),
                prepareUpdate(firstName, lastName, age, sex),
                FindAndModifyOptions.options().returnNew(true).upsert(false),
                Patient::class.java
        )
    }

    private fun prepareUpdate(firstName: String?, lastName: String?, age: Int?, sex: Sex?) : Update {
        val update = Update()
        if (lastName != null) update.set("lastName", lastName)
        if (firstName != null) update.set("firstName", firstName)
        if (age != null) update.set("age", age)
        if (sex != null) update.set("sex", sex)
        return update
    }
}
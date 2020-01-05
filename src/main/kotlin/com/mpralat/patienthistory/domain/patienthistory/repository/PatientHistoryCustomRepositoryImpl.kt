package com.mpralat.patienthistory.domain.patienthistory.repository

import com.mpralat.patienthistory.domain.patienthistory.PatientHistoryEntry
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import java.util.Date

class PatientHistoryCustomRepositoryImpl(
        private val mongoTemplate: MongoTemplate
) : PatientHistoryCustomRepository {
    override fun updatePatientHistoryEntry(id: String, modifiedAt: Date, problemDescription: String?, prescriptions: List<String>?): PatientHistoryEntry? {
        return mongoTemplate.findAndModify(
                Query().addCriteria(Criteria.where("_id").`is`(id)),
                prepareUpdate(modifiedAt, problemDescription, prescriptions),
                FindAndModifyOptions.options().returnNew(true).upsert(false),
                PatientHistoryEntry::class.java
        )
    }

    private fun prepareUpdate(modifiedAt: Date, problemDescription: String?, prescriptions: List<String>?): Update {
        val update = Update()
        update.set("modifiedAt", modifiedAt)
        if (problemDescription != null) update.set("problemDescription", problemDescription)
        if (prescriptions != null) {
            for (prescription in prescriptions) {
                update.addToSet("prescriptions", prescription)
            }
        }
        return update
    }
}
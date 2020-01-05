package com.mpralat.patienthistory.domain.doctor

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "doctors")
data class Doctor(
        @Id val id: String,
        val firstName: String,
        val lastName: String,
        val specialties: List<Specialty>
)

enum class Specialty {
    ALLERGOLOGIST,
    ANDROLOGIST,
    ANESTHESIOLOGIST,
    CARDIOLOGIST,
    DERMATOLOGIST,
    DIABETOLOGIST,
    ENDOCRINOLOGIST,
    GASTROENTEROLOGIST,
    GENERAL_PRACTITIONER,
    GERIATRICIAN,
    GYNAECOLOGIST,
    HEMATOLOGIST,
    INTERNIST,
    NEPHROLOGIST,
    NEUROLOGIST,
    OBSTETRICIAN,
    ONCOLOGIST,
    OTOLARYNGOLOGIST,
    PEDIATRICIAN,
    PSYCHIATRIST,
    PULMONOLOGIST,
    RADIOLOGIST,
    SURGEON,
    UROLOGIST,
}
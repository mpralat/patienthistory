package com.mpralat.patienthistory.domain.patient

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "patients")
data class Patient constructor(
        @Id val id: String,
        val firstName: String,
        val lastName: String,
        val sex: Sex,
        val age: Int,
        @Indexed(background = true) val pesel: String
)

enum class Sex {
    FEMALE,
    MALE,
    OTHER;

    companion object {
        fun fromString(value: String) =
                when (value.toUpperCase()) {
                    "F", "K" -> FEMALE
                    "M" -> MALE
                    "O", "I", "INNA", "OTHER" -> OTHER
                    else -> {
                        throw IllegalArgumentException("Incorrect value provided! No such sex: $value")
                    }
                }
    }
}
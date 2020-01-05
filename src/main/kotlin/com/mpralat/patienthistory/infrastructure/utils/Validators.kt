package com.mpralat.patienthistory.infrastructure.utils

class Constants {
    companion object {
        val PESEL_REGEX = Regex("[0-9]{11}")
        val NAME_REGEX = Regex("^[a-z ,.'-]+\$")
    }
}
fun validatePeselNumber(pesel: String): Boolean = Constants.PESEL_REGEX.matches(pesel)

fun validateName(name: String): Boolean = Constants.NAME_REGEX.matches(name)
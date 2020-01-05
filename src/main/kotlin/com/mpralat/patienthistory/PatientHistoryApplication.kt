package com.mpralat.patienthistory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PatientHistoryApplication

fun main(args: Array<String>) {
    runApplication<PatientHistoryApplication>(*args)
}

package com.mpralat.patienthistory.domain

import java.util.Date

interface DateProvider {
    fun getDate(): Date
}
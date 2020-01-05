package com.mpralat.patienthistory.infrastructure.utils

import com.mpralat.patienthistory.domain.DateProvider
import org.springframework.stereotype.Component
import java.util.*

@Component
class CurrentDateProvider : DateProvider {
    override fun getDate(): Date = Date()
}
package com.mpralat.patienthistory.infrastructure.utils

import com.mpralat.patienthistory.domain.IdProvider
import org.springframework.stereotype.Component
import java.util.*

@Component
class UUIDProvider : IdProvider {
    override fun getId(): String = UUID.randomUUID().toString()
}

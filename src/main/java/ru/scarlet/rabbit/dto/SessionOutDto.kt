package ru.scarlet.rabbit.dto

import java.util.*

class SessionOutDto {
    var sessionId: UUID = UUID.randomUUID()
    var sessionName: String = "Session ##"
    var customerId: UUID = UUID.randomUUID()
    var startedAt: Long = System.currentTimeMillis()
    var updatedAt: Long? = null
    var isActive: Boolean = true
}
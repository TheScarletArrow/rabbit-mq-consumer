package ru.scarlet.rabbit.event

import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "sessions")
class CurrentSession {
    val sessionId: UUID = UUID.randomUUID()
    val sessionName: String = "Session ##"
    val customerId: UUID = UUID.randomUUID()
    val startedAt: Long = System.currentTimeMillis()
    val isActive: Boolean = true
    override fun toString(): String {
        return "CurrentSession(sessionId=$sessionId, sessionName='$sessionName', customerId=$customerId, startedAt=$startedAt, isActive=$isActive)"
    }
}
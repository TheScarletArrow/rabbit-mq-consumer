package ru.scarlet.rabbit.event

import lombok.AllArgsConstructor
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "sessions")
@AllArgsConstructor
class CurrentSession {
    var id: UUID = UUID.randomUUID()
    var sessionName: String = "Session ##"
    var customerId: UUID = UUID.randomUUID()
    var startedAt: Long = System.currentTimeMillis()
    var updatedAt: Long? = null
    var isActive: Boolean? = null
    override fun toString(): String {
        return "CurrentSession(sessionId=$id, sessionName='$sessionName', customerId=$customerId, startedAt=$startedAt, isActive=$isActive)"
    }
}
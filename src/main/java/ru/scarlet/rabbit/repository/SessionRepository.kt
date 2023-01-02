package ru.scarlet.rabbit.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.scarlet.rabbit.event.CurrentSession
import java.util.UUID

interface SessionRepository : MongoRepository<CurrentSession, UUID> {
}

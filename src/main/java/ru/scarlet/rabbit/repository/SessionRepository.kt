package ru.scarlet.rabbit.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.scarlet.rabbit.event.CurrentSession
import java.util.*

@Repository
interface SessionRepository : MongoRepository<CurrentSession, UUID> {
fun findAllByIsActive(isActive: Boolean): List<CurrentSession>


    fun findByIsActiveTrue(): List<CurrentSession>

    fun findByIsActiveFalse(): List<CurrentSession>

}

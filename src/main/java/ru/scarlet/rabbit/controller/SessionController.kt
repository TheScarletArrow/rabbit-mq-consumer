package ru.scarlet.rabbit.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.scarlet.rabbit.dto.SessionOutDto
import ru.scarlet.rabbit.exception.SessionNotFoundException
import ru.scarlet.rabbit.service.SessionService
import java.util.*

@RestController
@RequestMapping("/session")
class SessionController(private val sessionService: SessionService) {

    @GetMapping("/{id}")
    fun getSessionById(@PathVariable id: UUID): ResponseEntity<SessionOutDto> {
        try {
            val session = sessionService.getSessionById(id)
            return ResponseEntity.ok(session)
        } catch (e: SessionNotFoundException) {
            throw e
        }
    }

    @GetMapping("/{id}/stop")
    fun stopSessionById(@PathVariable id: UUID): ResponseEntity<SessionOutDto> {
        try {
            sessionService.stopSessionByUUID(id)

            return ResponseEntity.ok().build()
        } catch (e: SessionNotFoundException) {
            throw e
        }
    }

    @GetMapping("/")
    fun getAllSessions(): ResponseEntity<List<SessionOutDto>> {
        return ResponseEntity.ok(sessionService.getAll())
    }

}
package ru.scarlet.rabbit.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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
            var stopSessionByUUID = sessionService.stopSessionByUUID(id)

            val httpHeaders: HttpHeaders = HttpHeaders()
            httpHeaders.add("Location", "/session/${id}")
            return ResponseEntity.status(200).headers(httpHeaders).body(stopSessionByUUID)
        } catch (e: SessionNotFoundException) {
            throw e
        }
    }

    @GetMapping
    fun getAllSessionsByUser(@RequestParam isActive: String): ResponseEntity<List<SessionOutDto>> {


        return when (isActive) {
            "true" -> ResponseEntity.ok(sessionService.getAllActive())
            "false" -> ResponseEntity.ok(sessionService.getAllInactive())
            else -> ResponseEntity.ok(sessionService.getAll())
        }

    }
}
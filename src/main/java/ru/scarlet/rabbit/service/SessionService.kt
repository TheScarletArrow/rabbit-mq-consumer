package ru.scarlet.rabbit.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.scarlet.rabbit.dto.SessionOutDto
import ru.scarlet.rabbit.exception.SessionNotFoundException
import ru.scarlet.rabbit.mapper.SessionMapper
import ru.scarlet.rabbit.repository.SessionRepository
import java.time.Instant
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Service
class SessionService @Autowired constructor(
    private val sessionRepository: SessionRepository,
    private val sessionMapper: SessionMapper,
)
{

    fun stopSessionByUUID(uuid: UUID) {

        var session: SessionOutDto = getSessionById(uuid)
        session.isActive = false
        session.updatedAt = Instant.now().toEpochMilli()
        sessionRepository.save(sessionMapper.toEntity(session))
    }

    fun getSessionById(id: UUID): SessionOutDto {
        return sessionMapper.toDto(sessionRepository.findById(id).getOrElse {
            throw SessionNotFoundException(id)
        })
    }

    fun getAll(): List<SessionOutDto>? {
        return sessionMapper.toDtoList(sessionRepository.findAll())
    }
}
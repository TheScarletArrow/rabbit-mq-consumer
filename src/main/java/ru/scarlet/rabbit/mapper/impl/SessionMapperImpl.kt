package ru.scarlet.rabbit.mapper.impl

import org.springframework.stereotype.Service
import ru.scarlet.rabbit.dto.SessionOutDto
import ru.scarlet.rabbit.event.CurrentSession
import ru.scarlet.rabbit.mapper.SessionMapper
import java.util.Currency

@Service
class SessionMapperImpl : SessionMapper {
    override fun toDto(session: CurrentSession): SessionOutDto {
        return SessionOutDto().apply {
            sessionId = session.id
            sessionName = session.sessionName
            customerId = session.customerId
            startedAt = session.startedAt
            updatedAt = session.updatedAt
            isActive = session.isActive
        }
    }

    override fun toEntity(sessionOutDto: SessionOutDto): CurrentSession {
        return CurrentSession().apply {
            id = sessionOutDto.sessionId
            sessionName = sessionOutDto.sessionName
            customerId = sessionOutDto.customerId
            startedAt = sessionOutDto.startedAt
            updatedAt = sessionOutDto.updatedAt
            isActive = sessionOutDto.isActive
        }
    }

    override fun toDtoList(sessions: List<CurrentSession>): List<SessionOutDto> {
        return sessions.map { toDto(it) }
    }
}
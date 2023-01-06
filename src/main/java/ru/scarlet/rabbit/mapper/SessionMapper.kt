package ru.scarlet.rabbit.mapper

import org.mapstruct.Mapper
import ru.scarlet.rabbit.dto.SessionOutDto
import ru.scarlet.rabbit.event.CurrentSession

interface SessionMapper {

    fun toDto(session: CurrentSession): SessionOutDto

    fun toEntity(sessionOutDto: SessionOutDto): CurrentSession

    fun toDtoList(sessions: List<CurrentSession>): List<SessionOutDto>
}
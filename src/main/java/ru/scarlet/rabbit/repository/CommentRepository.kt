package ru.scarlet.rabbit.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.scarlet.rabbit.event.Comment
import java.util.*

interface CommentRepository : MongoRepository<Comment, UUID> {
    fun findByPostId(postId: UUID): Optional<List<Comment>>
}

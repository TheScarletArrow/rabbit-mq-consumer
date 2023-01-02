package ru.scarlet.rabbit.service

import org.springframework.stereotype.Service
import ru.scarlet.rabbit.dto.CommentInDto
import ru.scarlet.rabbit.event.Comment
import ru.scarlet.rabbit.exception.CommentNotFoundException
import ru.scarlet.rabbit.repository.CommentRepository
import java.time.Instant
import java.util.*
import kotlin.jvm.optionals.getOrElse
import kotlin.random.Random

@Service
class CommentService(
    private val commentRepository: CommentRepository,
) {

    fun saveCommentByDto(commentInDto: CommentInDto) : Comment {
        var comment = Comment()
        comment.author = commentInDto.author
        comment.content = commentInDto.content
        comment.postId = commentInDto.postId
        comment.createdAt = Instant.now().toEpochMilli()
       return commentRepository.save(comment)
    }

    fun saveComment(comment: Comment) {

        comment.updatedAt = if (Random.nextBoolean()) Instant.now().toEpochMilli() else null
        commentRepository.save(comment)
    }

    fun getAll(): List<Comment>? =
        commentRepository.findAll()

    fun getCommentById(id: UUID): Comment? {
        return commentRepository.findById(id).getOrElse {throw CommentNotFoundException(id) }
    }

}
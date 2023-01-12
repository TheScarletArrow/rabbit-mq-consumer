package ru.scarlet.rabbit.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.scarlet.rabbit.dto.CommentInDto
import ru.scarlet.rabbit.event.Comment
import ru.scarlet.rabbit.exception.CommentNotFoundException
import ru.scarlet.rabbit.service.CommentService
import java.util.*

@RestController
@RequestMapping(value = ["/comment"])
class CommentController(private val commentService: CommentService) {

    @GetMapping("/")
    fun getComments(): ResponseEntity<List<Comment>> {
        return ResponseEntity.ok(commentService.getAll())
    }

    @PostMapping("/")
    fun createComment(@RequestBody comment: CommentInDto): ResponseEntity<Comment> {
        val saveComment = commentService.saveCommentByDto(comment)
        return ResponseEntity.ok(saveComment)
    }

    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id: UUID): ResponseEntity<Comment> {
       try {
           val comment = commentService.getCommentById(id)
           return ResponseEntity.ok(comment)
       } catch (e: CommentNotFoundException) {
           throw e
       }
    }

    @DeleteMapping("/{id}")
    fun deleteCommentById(@PathVariable id: UUID): ResponseEntity<Comment> {
        try {
            val comment = commentService.deleteCommentById(id)
            return ResponseEntity.ok(comment)
        } catch (e: CommentNotFoundException) {
            throw e
        }
    }
}

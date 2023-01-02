package ru.scarlet.rabbit.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.scarlet.rabbit.dto.CommentInDto
import ru.scarlet.rabbit.event.Comment
import ru.scarlet.rabbit.service.CommentService

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
}

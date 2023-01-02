package ru.scarlet.rabbit.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(PostNotFoundException::class)
    fun handlePostNotFoundException(e: PostNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse()
        errorResponse.message = e.message
        errorResponse.status = 404
        errorResponse.timestamp = System.currentTimeMillis()
        errorResponse.path = "/post"
        return ResponseEntity.status(errorResponse.status!!).body(errorResponse)
    }

    @ExceptionHandler(CommentNotFoundException::class)
    fun handleCommentNotFoundException(e: CommentNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse()
        errorResponse.message = e.message
        errorResponse.status = 404
        errorResponse.timestamp = System.currentTimeMillis()
        errorResponse.path = "/comment"
        return ResponseEntity.status(errorResponse.status!!).body(errorResponse)
    }
}
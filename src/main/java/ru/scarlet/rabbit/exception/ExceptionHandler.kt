package ru.scarlet.rabbit.exception

import org.springframework.http.HttpHeaders
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
        var headers = HttpHeaders()
        headers.add("Error", "${e.message}")
        return ResponseEntity.status(errorResponse.status!!).headers(headers).body(errorResponse)    }

    @ExceptionHandler(CommentNotFoundException::class)
    fun handleCommentNotFoundException(e: CommentNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse()
        errorResponse.message = e.message
        errorResponse.status = 404
        errorResponse.timestamp = System.currentTimeMillis()
        errorResponse.path = "/comment"
        var headers = HttpHeaders()
        headers.add("Error", "${e.message}")
        return ResponseEntity.status(errorResponse.status!!).headers(headers).body(errorResponse)    }

    @ExceptionHandler(SessionNotFoundException::class)
    fun handleSessionNotFoundException(e: SessionNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse()
        errorResponse.message = e.message
        errorResponse.status = 404
        errorResponse.timestamp = System.currentTimeMillis()
        errorResponse.path = "/session"
        var headers = HttpHeaders()
        headers.add("Error", "${e.message}")
        return ResponseEntity.status(errorResponse.status!!).headers(headers).body(errorResponse)    }

    @ExceptionHandler(CanNotStopSessionException::class)
    fun handleCanNotStopSessionException(e: CanNotStopSessionException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse()
        errorResponse.message = e.message
        errorResponse.status = 400
        errorResponse.timestamp = System.currentTimeMillis()
        errorResponse.path = "/session"
        var headers = HttpHeaders()
        headers.add("Error", "${e.message}")
        return ResponseEntity.status(errorResponse.status!!).headers(headers).body(errorResponse)
    }
}
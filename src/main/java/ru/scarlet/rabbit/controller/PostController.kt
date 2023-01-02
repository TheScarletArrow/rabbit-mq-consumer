package ru.scarlet.rabbit.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.scarlet.rabbit.dto.PostInDto
import ru.scarlet.rabbit.event.Post
import ru.scarlet.rabbit.service.PostService
import java.util.UUID

@RestController
@RequestMapping("/post")
class PostController(private val postService: PostService) {

    @GetMapping("/")
    fun getPosts(): ResponseEntity<List<Post>> {
        return ResponseEntity.ok(postService.getAll())
    }

    @PostMapping("/")
    fun createPost(@RequestBody post: PostInDto): ResponseEntity<Post> {

        val savePost = postService.savePost(post)
        return ResponseEntity.ok(savePost)
    }

    @PutMapping("/{id}")
    fun updatePost(@RequestBody post: PostInDto, @PathVariable id: UUID): ResponseEntity<Post> {
        val savePost = postService.updatePost(post, id)
        return ResponseEntity.ok(savePost)
    }

//    @GetMapping("/{id}")
//    fun getPostById(@PathVariable id: UUID): ResponseEntity<Post> {
//        val post = postService.getPostById(id)
//        return ResponseEntity.ok(post)
//    }
}
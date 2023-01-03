package ru.scarlet.rabbit.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.scarlet.rabbit.dto.PostInDto
import ru.scarlet.rabbit.event.Comment
import ru.scarlet.rabbit.event.Post
import ru.scarlet.rabbit.event.PostStatus
import ru.scarlet.rabbit.exception.PostNotFoundException
import ru.scarlet.rabbit.repository.CommentRepository
import ru.scarlet.rabbit.repository.PostRepository
import java.time.Instant
import java.util.*
import kotlin.jvm.optionals.getOrElse
import kotlin.random.Random

@Service
class PostService @Autowired constructor(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository
) {
    fun getPostByTitle(title: String): Post {
        return postRepository.findByTitle(title)
    }

    fun savePost(post: Post) : Post = postRepository.save(post)
    fun savePostByDto(postInDto: PostInDto) : Post {
        //random boolean
        var post = Post()
        post.createdAt = Instant.now().toEpochMilli()
        post.body = postInDto.body
        post.title = postInDto.title
        post.author = postInDto.author
        post.title = postInDto.title
        post.updatedAt = if (Random.nextBoolean()) Instant.now().toEpochMilli() else null
        post.status = PostStatus.PUBLISHED
        return postRepository.save(post)
    }

    fun getAll(): List<Post> {
        return postRepository.findAll()
    }

    fun updatePost(postInDto: PostInDto, id: UUID): Post? {
        postRepository.findById(id).getOrElse {
            throw PostNotFoundException(id)
        }.let {
            val post = postRepository.findById(id).get()
            post.body = postInDto.body ?: post.body
            post.title = postInDto.title ?: post.title
            post.author = postInDto.author ?: post.author
            post.title = postInDto.title ?: post.title
            post.updatedAt = Instant.now().toEpochMilli()
            return postRepository.save(post)
        }
    }

    fun getPostById(id: UUID): Post? {
        return postRepository.findById(id).getOrElse { throw PostNotFoundException(id) }
    }

    fun getCommentsByPostId(id: UUID): List<Comment>? {
        commentRepository.findByPostId(id).getOrElse{
            throw PostNotFoundException(id)
        }.let {
            return commentRepository.findByPostId(id).get()
        }
    }


}

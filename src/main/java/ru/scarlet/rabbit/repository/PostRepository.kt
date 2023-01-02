package ru.scarlet.rabbit.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.scarlet.rabbit.event.Post
import java.util.*

interface PostRepository : MongoRepository<Post, UUID> {
    fun findByTitle(title: String): Post


}
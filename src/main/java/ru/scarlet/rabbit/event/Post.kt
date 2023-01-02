package ru.scarlet.rabbit.event

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "posts")
class Post() {
    var id: UUID? = UUID.randomUUID()
    var title: String? = null
    var content: String? = null
    var author: String? = null
    var createdAt: Long? = null
    var updatedAt: Long? = null
    var body: String? = null
    override fun toString(): String {
        return "Post(id=$id, title=$title, content=$content, author=$author, createdAt=$createdAt, updatedAt=$updatedAt, body=$body)"
    }

}

@Document(collection = "comments")
class Comment
    () {
    var id: UUID? = UUID.randomUUID()
    var postId: UUID? = null
    var content: String? = null
    var author: String? = null
    var createdAt: Long? = null
    var updatedAt: Long? = null
    override fun toString(): String {
        return "Comment(id=$id, postId=$postId, content=$content, author=$author, createdAt=$createdAt, updatedAt=$updatedAt)"
    }

}
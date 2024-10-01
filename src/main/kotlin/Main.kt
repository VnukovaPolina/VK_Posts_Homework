package ru.netology

import ru.netology.WallService.newPostId

data class Likes(var likesCount: Int = 0)

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean = false,
    val likes: Likes,
    val reposts: Int = 0,
    val views: Int
    )

object WallService {
    private var posts = emptyArray<Post>()
    var newPostId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++newPostId, likes = post.likes.copy())
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postsForSearch) in posts.withIndex()) {
            if (posts[index].id == post.id) {
                posts[index] = post.copy(likes = post.likes.copy())
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        newPostId = 0
    }
}

fun main() {
    val likes = Likes(5)
    val post = Post(id = 1,
        ownerId = 18456,
        fromId = 18456,
        date = 1727635689,
        text = "Hello world!",
        likes = likes,
        reposts = 0,
        views = 0
    )
    WallService.add(post)
}
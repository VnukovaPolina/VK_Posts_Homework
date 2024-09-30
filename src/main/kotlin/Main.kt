package ru.netology

data class Likes(var likesCount: Int = 0)

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val likes: Likes,
    val reposts: Int,
    val views: Int
    )

object WallService {
    private var posts = emptyArray<Post>()
    private var newPostId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++newPostId,
            ownerId = 18456,
            fromId = 18456,
            date = 1727635689,
            text = "Hello world!",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            likes = post.likes.copy(),
            reposts = 0,
            views = 0)

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
}

fun main() {
    println("Hello World!")
}
package ru.netology

interface Attachment {
    val type : String
}

class Photo(val id : Int,
            val ownerId : Int,
            val photo_130 : String,
            val photo_604 : String)

class Video(val id : Int,
            val ownerId : Int,
            val title : String,
            val duration : Int)

class Audio(val id : Int,
            val ownerId : Int,
            val artist : String,
            val title : String,
            val duration : Int)

class Poll(val id : Int,
           val ownerId : Int,
           val question : String,
           val anonymous : Boolean)

class Product(val id : Int,
              val ownerId : Int,
              val title : String,
              val price : Int)

class PhotoAttachment : Attachment {
    override val type = "photo"
    var photo = Photo(id = 1,
        ownerId = 13240,
        photo_130 = "https://vk.com/preview_link",
        photo_604 = "https://vk.com/full_photo_link")
}

class VideoAttachment : Attachment {
    override val type = "video"
    var video = Video(id = 1,
        ownerId = 24871,
        title = "Cute cat",
        duration = 75)
}

class AudioAttachment : Attachment {
    override val type = "audio"
    var audio = Audio(id = 1,
        ownerId = 19652,
        artist = "The Offspring",
        title = "Pretty Fly (For A White Guy)",
        duration = 184)
}

class PollAttachment : Attachment {
    override val type = "poll"
    var poll = Poll(id = 1,
        ownerId = 72119,
        question = "Do you love bacon with fried eggs?",
        anonymous = false)
}

class ProductAttachment : Attachment {
    override val type = "product"
    var product = Product(id = 1,
        ownerId = 28121,
        title = "Pretty hat",
        price = 1900)
}

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
    val views: Int,
    val attachments: Array<Attachment> = emptyArray<Attachment>()
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
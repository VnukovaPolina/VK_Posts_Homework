import ru.netology.Likes
import ru.netology.Post
import ru.netology.WallService
import ru.netology.WallService.newPostId
import ru.netology.WallService.update
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WallServiceTest {

    @BeforeTest
    fun setUp() {
        WallService.clear()
    }

    @Test
    fun addPost() {
        val post = Post(id = ++newPostId,
            ownerId = 18456,
            fromId = 18456,
            date = 1727635689,
            text = "Hello world!",
            likes = Likes(0),
            reposts = 0,
            views = 0)

        val newPost = WallService.add(post)
        val isAdded = if (newPost.id != 0) true else false
        assertTrue(isAdded)
    }

    @Test
    fun updateExistingPost() {
        val post = Post(id = ++newPostId,
            ownerId = 18456,
            fromId = 18456,
            date = 1727635689,
            text = "Hello world!",
            likes = Likes(0),
            reposts = 0,
            views = 0)

        val newPost = WallService.add(post)
        val isSuccessForExistingPost = update(newPost)
        assertTrue(isSuccessForExistingPost)
    }

    @Test
    fun updateNotExistingPost() {
        val post = Post(id = ++newPostId,
            ownerId = 18456,
            fromId = 18456,
            date = 1727635689,
            text = "Hello world!",
            likes = Likes(0),
            reposts = 0,
            views = 0)

        val isSuccessForExistingPost = update(post)
        assertFalse(isSuccessForExistingPost)
    }
}
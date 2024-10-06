import ru.netology.*
import ru.netology.WallService.add
import ru.netology.WallService.createComment
import ru.netology.WallService.findById
import ru.netology.WallService.newCommentId
import ru.netology.WallService.newPostId
import ru.netology.WallService.posts
import ru.netology.WallService.update
import kotlin.test.*

class WallServiceTest {

    @BeforeTest
    fun setUp() {
        WallService.clear()
    }

    @Test
    fun addPostTest() {
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

    @Test
    fun createCommentForExistingPost() {
        val postID = 1
        val post = Post(id = postID,
            ownerId = 18456,
            fromId = 18456,
            date = 1727635689,
            text = "Hello world!",
            likes = Likes(0),
            reposts = 0,
            views = 0)
        add(post)
        val textForComment = "Comment for test"
        val comment = Comment(
            id = ++newCommentId,
            fromId = 32188,
            text = textForComment,
            date = 1827635587
        )
        createComment(postID, comment)
        val postWithComment = findById(postID)
        val actualLastComment = postWithComment?.comments?.lastIndex
        assertNotNull(actualLastComment)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentForNOTExistingPost() {
        val postID = 100
        val post = Post(id = postID,
            ownerId = 18456,
            fromId = 18456,
            date = 1727635689,
            text = "Hello world!",
            likes = Likes(0),
            reposts = 0,
            views = 0)
        add(post)
        val textForComment = "Comment for test"
        val comment = Comment(
            id = ++newCommentId,
            fromId = 32188,
            text = textForComment,
            date = 1827635587
        )
        createComment(postID, comment)
    }
}
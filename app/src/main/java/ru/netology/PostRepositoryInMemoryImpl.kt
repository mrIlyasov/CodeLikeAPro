package ru.netology

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            1,
            "Нетология1111. Университет интернет-профессий",
            "27 октября в 22:19",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с" +
                    " интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                    "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                    "уверенных профессионалов.Привет, это новая Нетология! Когда-то Нетология начиналась с " +
                    "интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                    "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                    "уверенных профессионалов.", 5999, 100, 100
        ),
        Post(
            2,
            "Нетология. Университет интернет-профессий",
            "27 октября в 22:19",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с" +
                    " интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                    "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                    "интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                    "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                    "уверенных профессионалов.", 1999, 100, 100

        )
    )


    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data


    override fun repost(id: Int) {
        var post = findPostById(id)
        if (post != null) {
            val currentReposts = post.repostsCount
            post = post.copy(repostsCount = currentReposts + 10)
            data.value = posts
        }
    }

    override fun view(id: Int) {
        var post = findPostById(id)
        if (post != null) {
            post = post.copy(views = post.views + 1)
        }
        data.value = posts
    }


    override fun addLikesRepostsViews(id: Int) {

        var post = findPostById(id)

        val newList = posts.toCollection(mutableListOf())

        if (post != null) {
            val currentLikes = post.likes
            val currentReposts = post.repostsCount
            val currentViews = post.views
            var newPost = post.copy(
                likes = currentLikes + 899,
                repostsCount = currentReposts + 990,
                views = currentViews + 10000
            )
            newList[0] = newPost
            posts = newList
        }
        data.value = posts
    }


    override fun likeById(id: Int) {
        var post = posts.find { it.id == id }
        if (post != null) {
            val currentLikes = post.likes

            if (!post.likedByMe) {

                post = post.copy(likedByMe = true, likes = currentLikes + 1)


            } else {
                post = post.copy(likedByMe = false, likes = currentLikes - 1)
            }
        }
        data.value = posts
    }

    override fun like(id: Int) {
        var post = findPostById(id)
        if (post != null) {
            var currentLikes = post.likes
            if (!post.likedByMe) {
                post = post.copy(likedByMe = true, likes = currentLikes + 1)

            } else {
                post = post.copy(likedByMe = false, likes = currentLikes - 1)
            }
        }

    }

    override fun findPostById(id: Int): Post? {
        var list = data.value
        if (list != null)
            return list.find { it.id == id }
        else return null
    }
}
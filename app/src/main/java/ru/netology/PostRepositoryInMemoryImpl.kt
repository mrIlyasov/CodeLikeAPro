package ru.netology

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl : PostRepository {
    var post: Post = Post(
        1,
        "Нетология. Университет интернет-профессий",
        "27 октября в 22:19",
        "Привет, это новая Нетология! Когда-то Нетология начиналась с" +
                " интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                "уверенных профессионалов.Привет, это новая Нетология! Когда-то Нетология начиналась с " +
                "интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                "уверенных профессионалов.", 999, 100, 100
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        val currentLikes = post.likes
        if (!post.likedByMe) {
            post = post.copy(likedByMe = true, likes = currentLikes + 1)

        } else {
            post = post.copy(likedByMe = false, likes = currentLikes - 1)
        }

        data.value = post
    }


    override fun repost() {
        val currentReposts = post.repostsCount
        post = post.copy(repostsCount = currentReposts + 10)
        data.value = post
    }

    override fun view() {
        post = post.copy(views = post.views + 1)
        data.value = post
    }


    override fun addLikesRepostsViews() {
        val currentLikes = post.likes
        val currentReposts = post.repostsCount
        val currentViews = post.views
        post = post.copy(
            likes = currentLikes + 899,
            repostsCount = currentReposts + 990,
            views = currentViews + 10000
        )
        data.value = post
    }

}
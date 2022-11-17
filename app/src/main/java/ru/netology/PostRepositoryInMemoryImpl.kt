package ru.netology

import android.app.Application
import android.content.Context
import android.widget.Toast
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
                    "уверенных профессионалов.", 19, 100, 100
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
                    "уверенных профессионалов.", 1999, 1050, 100

        ),
        Post(
            3,
            "Нетология222. Университет интернет-профессий",
            "27 октября в 22:19",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с" +
                    " интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                    "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                    "интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                    "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                    "уверенных профессионалов.", 2999, 100, 1000

        )
    )


    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data


    override fun repost(id: Int) {
        posts = posts.map {
            if (it.id == id) it.copy(repostsCount = it.repostsCount + 1)
            else it
        }
        data.value = posts

    }

    override fun view(id: Int) {
        var post = data.value?.find { it.id == id }
        if (post != null) {
            post = post.copy(views = post.views + 1)
        }
        data.value = posts
    }


    override fun addLikesRepostsViews(id: Int) {

        posts = posts.map {
            if (it.id == id) it.copy(
                likes = it.likes + 1000,
                repostsCount = it.repostsCount + 200,
                views = it.views + 10000
            ) else it
        }
        data.value = posts
    }


    override fun like(id: Int) {
        posts = posts.map {
            if (it.id != id) it else
                if (it.likedByMe == false) {
                    it.copy(likedByMe = !it.likedByMe, likes = it.likes + 1)
                } else it.copy(likedByMe = !it.likedByMe, likes = it.likes - 1)
        }
        data.value = posts
    }


}
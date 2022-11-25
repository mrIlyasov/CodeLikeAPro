package ru.netology

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl : PostRepository {
    var nextId = 1
    private var posts = listOf(
        Post(
            nextId++,
            "Нетология. Университет интернет-профессий",
            "27 октября в 22:19 " + nextId,
            "Привет, это новая Нетология! Когда-то Нетология начиналась с" +
                    " интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                    "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                    "уверенных профессионалов.Привет, это новая Нетология! Когда-то Нетология начиналась с " +
                    "интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                    "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                    "уверенных профессионалов.", 19, 100, 100
        ),
        Post(
            nextId++,
            "Нетология. Университет интернет-профессий",
            "27 октября в 22:19 $nextId",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с" +
                    " интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                    "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                    "интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                    "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                    "уверенных профессионалов.", 1999, 1050, 100

        ),
        Post(
            nextId++,
            "Нетология. Университет интернет-профессий",
            "27 октября в 22:19 $nextId",
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
    override fun getSize(): Int {
        return posts.size
    }

    override fun repost(id: Int) {
        posts = posts.map {
            if (it.id == id) it.copy(repostsCount = it.repostsCount + 1)
            else it
        }
        data.value = posts

    }

    override fun view(id: Int) {

        posts = posts.map {
            if (it.id == id) it.copy(views = it.views + 1)
            else it
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

    override fun removeById(id: Int) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun edit(id: Int, newContent: String) {
        posts = posts.map {
            if (it.id == id) it.copy(
                content = newContent
            ) else it
        }
        data.value = posts

    }

    override fun savePost(post: Post) {

        var newPosts = mutableListOf<Post>()
        if (posts.find { it.id == post.id } == null) {
            newPosts.addAll(posts)
            newPosts.add(
                post.copy(
                    nextId++,
                    authorName = "Me",
                    likedByMe = false,
                    date = "now"
                )
            )
        } else
            newPosts = posts.map {
                if (it.id == post.id) it.copy(content = post.content) else it
            }.toMutableList()
        posts = newPosts
        data.value = posts
    }
}






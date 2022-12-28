package ru.netology.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.dataClasses.Post

class PostRepositoryInFileImpl(
    private val context: Context,
) : PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val filename = "posts.json"

    private var nextId = 1
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)


    init {
        val file = context.filesDir.resolve(filename)

        if (file.exists()) {

            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)
                data.value = posts
            }
        } else {

            sync()
        }
    }


    override fun get(): LiveData<List<Post>> = data
    override fun getSize(): Int {
        return posts.size
    }

    override fun findPost(id: Int): Post? {
        return posts.find { it.id == id }

    }

    override fun findIndexOfPostById(id: Int): Int {
        return posts.indexOf(posts.find { it.id == id })
    }

    override fun edit(id: Int, newContent: String) {
        posts = posts.map {
            if (it.id == id) it.copy(
                content = newContent
            ) else it
        }
        data.value = posts

    }



    override fun repost(id: Int) {

        posts = posts.map {
            if (it.id == id)
                it.copy(repostsCount = it.repostsCount + 1)
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

    override fun savePost(post: Post) {
        if (post.id == 0) {

            posts = listOf(
                post.copy(
                    id = nextId++,
                    authorName = "Me",
                    likedByMe = false,
                    date = "now"
                )
            ) + posts
            data.value = posts
            sync()
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
        sync()
    }

    override fun like(id: Int) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
            )
        }
        data.value = posts
        sync()
    }

    override fun removeById(id: Int) {
        posts = posts.filter { it.id != id }
        data.value = posts
        sync()
    }

    private fun sync() {
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }
}
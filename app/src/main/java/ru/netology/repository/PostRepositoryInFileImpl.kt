package ru.netology.repository

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.MainActivity
import ru.netology.dataClasses.Post
import java.io.File
import java.io.FileOutputStream

class PostRepositoryInFileImpl(
    private val context: Context,
) : PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val filename = "posts.json"
    private val prefs = context.getSharedPreferences("nextId", Context.MODE_PRIVATE)
    private val key = "nextId"
    var nextId = prefs.getInt(key, 0)
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)


    init {
        nextId = prefs.getInt(key, 1).apply { }
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
        sync()

    }

    override fun view(id: Int) {

        posts = posts.map {
            if (it.id == id) it.copy(views = it.views + 1)
            else it
        }


        data.value = posts
        sync()
    }

    override fun savePost(post: Post) {
        var newPosts = mutableListOf<Post>()
        if (posts.find { it.id == post.id } == null) {
            newPosts.addAll(posts)
            newPosts.add(
                post.copy(
                    nextId,
                    authorName = "Me $nextId",
                    likedByMe = false,
                    date = "now"
                )
            )
            addNextId()

        } else
            newPosts = posts.map {
                if (it.id == post.id) it.copy(content = post.content) else it
            }.toMutableList()
        posts = newPosts
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

    override fun addNextId() {

        prefs.edit().apply {
            putInt(key, nextId+1)
            commit()
        }
        nextId = prefs.getInt(key, nextId)



    }

}
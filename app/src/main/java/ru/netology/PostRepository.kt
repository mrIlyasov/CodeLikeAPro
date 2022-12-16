package ru.netology

import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<List<Post>>
    fun getSize(): Int
    fun like(id: Int)
    fun repost(id: Int)
    fun view(id: Int)
    fun removeById(id: Int)
    fun edit(id: Int, newContent: String)
    fun savePost(post: Post)
    fun addLikesRepostsViews(id: Int)
    fun findIndexOfPostById(id:Int): Int
    fun findPost(id: Int): Post?

}
package ru.netology

import androidx.lifecycle.LiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun like(id: Int)
    fun repost(id: Int)
    fun view(id: Int)
    fun addLikesRepostsViews(id: Int)
    fun likeById(id: Int)
    fun findPostById(id: Int) : Post?
}
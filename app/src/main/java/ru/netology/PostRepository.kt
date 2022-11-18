package ru.netology

import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<List<Post>>
    fun like(id: Int)
    fun repost(id: Int)
    fun view(id: Int)
    fun addLikesRepostsViews(id: Int)
}
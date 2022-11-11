package ru.netology

import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun repost()
    fun view()
    fun addLikesRepostsViews()
}
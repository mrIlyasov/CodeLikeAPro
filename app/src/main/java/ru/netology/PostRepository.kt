package ru.netology

import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<List<Post>>
    fun like(id: Int)
    fun repost(id: Int)
    fun view(id: Int)
    fun removeById(id:Int)
    fun edit(id: Int)

    fun addLikesRepostsViews(id: Int)

}
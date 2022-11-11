package ru.netology

import androidx.lifecycle.ViewModel

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun addLikesRepostsViews() = repository.addLikesRepostsViews()
    fun repost() = repository.repost()
}
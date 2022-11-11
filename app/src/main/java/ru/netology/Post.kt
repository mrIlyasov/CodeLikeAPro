package ru.netology

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class Post(
    val id: Int,
    var authorName: String,
    val date: String,
    val content: String,
    val likes: Int,
    val repostsCount: Int,
    val views: Int,
    val likedByMe: Boolean = false
) {
}


package ru.netology.dataClasses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class Post(
    val id: Int = 0,
    var authorName: String = "Me",
    val date: String = "Now",
    val content: String,
    val likes: Int = 0,
    val repostsCount: Int = 0,
    val views: Int = 0,
    val likedByMe: Boolean = false,
    val youtubeVideo: String? = null
) {
}


package ru.netology

data class Post(val id: Int, var authorName: String, val date: String, var content: String, var likes: Int,  var repostsCount: Int, var views: Int, var likedByMe: Boolean= false) {
}
package ru.netology

typealias OnInteracionLitener = (post: Post) -> Unit

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onAddPost(post: Post) {}
    fun onRemove(post: Post) {}
    fun onEdit(post: Post)
    fun onRepost(post: Post)
    fun onView(post: Post)
}
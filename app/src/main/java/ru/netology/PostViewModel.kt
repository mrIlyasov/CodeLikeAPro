package ru.netology

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private val defaultPost = Post(
    content = ""
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    val edited = MutableLiveData(defaultPost)


    fun getSizeOfPosts(): Int = repository.getSize()
    fun like(id: Int) = repository.like(id)
    fun view(id: Int) = repository.view(id)
    fun addLikesRepostsViews(id: Int) = repository.addLikesRepostsViews(id)
    fun repost(id: Int) = repository.repost(id)
    fun removeById(id: Int) = repository.removeById(id);
    fun edit(id: Int, newContent: String) = repository.edit(id, newContent);
    fun savePost(post: Post) = repository.savePost(post);
    fun findPost(id: Int): Post? = repository.findPost(id)

    fun save() {
        edited.value?.let { repository.savePost(it) }
    }

}



package ru.netology

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.dataClasses.Post
import ru.netology.repository.PostRepository
import ru.netology.repository.PostRepositoryInMemoryImpl

private val defaultPost = Post(
    id = 0,
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
    fun findIndexOfPostById(id: Int):Int = repository.findIndexOfPostById(id)

    fun savePost(post: Post) = repository.savePost(post);
    fun findPost(id: Int): Post? = repository.findPost(id)

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun save() {
        edited.value?.let { repository.savePost(it) }
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun getIdOfEdited():Int{
        return edited.value!!.id

    }

}



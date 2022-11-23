package ru.netology

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.databinding.PostCardBinding

typealias OnLikeListener = (post: Post) -> Unit
typealias OnRepostListener = (post: Post) -> Unit
typealias OnRemoveListener = (post: Post) -> Unit
typealias OnEditListener = (post: Post) -> Unit



class PostAdapter(
    private val onLikeListener: OnLikeListener,
    private val onRepostListener: OnRepostListener,
    private val onRemoveListener: OnRemoveListener,
    private val onEditListener: OnRepostListener

) : ListAdapter<Post, PostViewHolder>(PostDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(
            binding,
            onLikeListener,
            onRepostListener,
            onRemoveListener,
            onEditListener
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}



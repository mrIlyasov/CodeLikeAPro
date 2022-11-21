package ru.netology

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.databinding.PostCardBinding

class PostViewHolder(
    private val binding: PostCardBinding,
    private val onLikeListener: OnLikeListener,
    private val onRepostListener: OnRepostListener,
    private val onRemoveListener: OnRemoveListener,
    private val onEditListener: OnEditListener,

    ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.authorName
            date.text = post.date
            content.text = post.content
            likesCountTextView.text = rounding(post.likes)
            viewsCountTextView.text = rounding(post.views)
            shareCountTextView.text = rounding(post.repostsCount)

            likeButton.setImageResource(
                if (post.likedByMe) R.drawable.liked_icon else R.drawable.heart_icon
            )
            likeButton.setOnClickListener {
                onLikeListener(post)
            }
            shareButton.setOnClickListener {
                onRepostListener(post)
            }
            viewsButton.setOnClickListener {
                onRemoveListener(post)
            }
            menuButton.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onRemoveListener(post)
                                true
                            }
                            R.id.edit -> {
                                onEditListener(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
        }
    }


    fun rounding(value: Int): String {
        if (value > 999 && value < 10000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var stringToReturn = "$symbol1" + "," + "$symbol2" + "K"
            return (stringToReturn)
        } else if (value >= 10000 && value < 100_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]

            var stringToReturn = "$symbol1" + "$symbol2" + "K"
            return (stringToReturn)
        } else if (value >= 100000 && value < 1_000_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var symbol3 = value.toString()[2]
            var stringToReturn = "$symbol1" + "$symbol2" + "$symbol3" + "K"
            return (stringToReturn)
        } else if (value > 1_000_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var stringToReturn = "$symbol1" + "," + "$symbol2" + "M"
            return (stringToReturn)
        } else return value.toString()
    }
}

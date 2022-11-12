package ru.netology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.netology.databinding.ActivityMainBinding
import ru.netology.databinding.PostCardBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()

        viewModel.data.observe(this) { posts ->
            posts.map { post ->
                PostCardBinding.inflate(layoutInflater, binding.container, false).apply {
                    author.text = post.authorName
                    date.text = post.date
                    content.text = post.content
                    shareCountTextView.text = rounding(post.repostsCount)
                    viewsCountTextView.text = rounding(post.views)
                    likesCountTextView.text = rounding(post.likes)
                    likeButton.setImageResource(if (post.likedByMe) R.drawable.liked_icon else R.drawable.heart_icon)
                    likeButton.setOnClickListener {
                        viewModel.like(post.id)
                    }
                }.root
            }.forEach { binding.container.addView(it) }
        }
        binding.button.setOnClickListener{
            viewModel.addLikesRepostsViews(1)
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


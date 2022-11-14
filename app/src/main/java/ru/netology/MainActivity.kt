package ru.netology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.allViews
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
        val adapter = PostAdapter {
        viewModel.like(it.id)
        }
        binding.list.adapter = adapter
        viewModel.data.observe(this){
            posts->
            adapter.submitList(posts)
        }
        binding.button.setOnClickListener{viewModel.addLikesRepostsViews(1)}

        /* viewModel.data.observe(this) { posts ->
             posts.map { post ->
                 PostCardBinding.inflate(layoutInflater, binding.list, true).apply {
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
                 }.root*/
    }
}






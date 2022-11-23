package ru.netology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.allViews
import androidx.core.view.size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.netology.databinding.ActivityMainBinding
import ru.netology.databinding.PostCardBinding

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post.id, binding.editContent.text.toString())
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onRepost(post: Post) {
                viewModel.repost(post.id)
            }

            override fun onView(post: Post) {
                viewModel.view(post.id)
            }

        })




    binding.list.adapter = adapter
    viewModel.data.observe(this)
    {
        posts ->
        adapter.submitList(posts)
    }
    binding.button.setOnClickListener()
    {
        viewModel.addLikesRepostsViews(1)
    }

    binding.saveButton.setOnClickListener()
    {
        with(binding.editContent) {
            if (text.isNullOrBlank()) {
                Toast.makeText(
                    this@MainActivity,
                    this@MainActivity.getString(R.string.error_empty_content),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.addPost(binding.editContent.text.toString())
                binding.editContent.text.clear()
                binding.list.smoothScrollToPosition(viewModel.getSizeOfPosts() - 1)
                clearFocus()
                AndroidUtils.hideKeyBoard(this)

            }
        }
    }
}
}






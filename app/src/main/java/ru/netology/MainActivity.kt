package ru.netology

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
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
    val defaultPost = Post(content = " ");
    var post = Post(content = " ");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.group.visibility = View.GONE
        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }

            override fun onEdit(post: Post) {
                binding.group.visibility = View.VISIBLE
                binding.editContent.requestFocus()
                binding.editContent.setText(post.content)
                binding.editTextView.setText(post.content)
                this@MainActivity.post = post.copy(content = post.content)

                AndroidUtils.showKeyBoard(binding.editContent)

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
        { posts ->
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
                    if (post.id == 0)
                        viewModel.savePost(post.copy(content = binding.editContent.text.toString()))
                    else {
                        if (post.id > 0 && viewModel.findPost(post.id) == null){
                            Toast.makeText(
                                this@MainActivity,
                                this@MainActivity.getString(R.string.error_post_not_found),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else
                            viewModel.savePost(post.copy(content = binding.editContent.text.toString()))
                    }
                    binding.editContent.text.clear()
                    binding.editContent.clearFocus()
                    binding.editContent.showSoftInputOnFocus
                    binding.list.smoothScrollToPosition(
                        if (viewModel.getSizeOfPosts() > 0) {
                            viewModel.getSizeOfPosts() - 1
                        } else {
                            0
                        }
                    )

                }
                AndroidUtils.hideKeyBoard(binding.editContent)
                binding.group.visibility = View.GONE
                this@MainActivity.post = defaultPost
            }
        }
        binding.cancelButton.setOnClickListener {
            binding.editContent.clearFocus()
            binding.editTextView.setText("")
            binding.group.visibility = View.GONE
            AndroidUtils.hideKeyBoard(binding.editContent)
            binding.editContent.setText("")
        }
    }
}






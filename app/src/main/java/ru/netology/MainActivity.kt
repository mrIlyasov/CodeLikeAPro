package ru.netology

import android.content.Context
import android.content.Intent
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

                viewModel.edit(post)
                AndroidUtils.showKeyBoard(binding.editContent)

            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onRepost(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent=Intent.createChooser(intent, post.content)
                startActivity(shareIntent)
                viewModel.repost(post.id)

            }

            override fun onView(post: Post) {
                viewModel.view(post.id)
            }

        })


        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        binding.button.setOnClickListener() {
            viewModel.addLikesRepostsViews(1)
        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0) {
                return@observe
            }


            with(binding.editContent) {
                requestFocus()
                setText(post.content)
            }
        }
        binding.saveButton.setOnClickListener() {
            val idOfEdited = viewModel.edited.value!!.id
            with(binding.editContent) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        this@MainActivity.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    viewModel.changeContent(text.toString())

                    viewModel.save()

                }
                binding.editContent.text.clear()
                binding.editContent.clearFocus()
                binding.editContent.showSoftInputOnFocus
                binding.list.smoothScrollToPosition(
                    (if (viewModel.getSizeOfPosts() > 0) {
                        viewModel.findIndexOfPostById(idOfEdited)
                    } else {
                        0
                    })
                )

            }
            AndroidUtils.hideKeyBoard(binding.editContent)
            binding.group.visibility = View.GONE

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






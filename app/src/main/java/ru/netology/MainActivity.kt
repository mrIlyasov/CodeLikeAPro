package ru.netology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels
import ru.netology.adapter.OnInteractionListener
import ru.netology.adapter.PostAdapter
import ru.netology.dataClasses.Post
import ru.netology.databinding.ActivityMainBinding
import ru.netology.utils.AndroidUtils
import ru.netology.activity.NewPostResultContract
import ru.netology.activity.NewPostActivity

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
                /*     binding.group.visibility = View.VISIBLE
                     binding.editContent.requestFocus()
                     binding.editContent.setText(post.content)
                     binding.editTextView.setText(post.content)*/
                val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
                    result ?: return@registerForActivityResult

                    viewModel.changeContent(result)
                    viewModel.save()


                }
                viewModel.edit(post)
                newPostLauncher.launch()
                //    AndroidUtils.showKeyBoard(binding.editContent)

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

                val shareIntent = Intent.createChooser(intent, post.content)
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


        viewModel.edited.observe(this) { post ->
            if (post.id == 0) {
                return@observe
            }


        }

        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult

            viewModel.changeContent(result)
            viewModel.save()
            binding.list.smoothScrollToPosition(
                (if (viewModel.getSizeOfPosts() > 0) {
                    viewModel.getSizeOfPosts()-1
                } else {
                    0
                })
            )
        }
        binding.fab.setOnClickListener() {

            newPostLauncher.launch()


        }
        /*   with(binding.editContent) {
               requestFocus()
               setText(post.content)
           }*/

        /*       binding.saveButton.setOnClickListener() {
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

               }*/

/*        binding.cancelButton.setOnClickListener {
            binding.editContent.clearFocus()
            binding.editTextView.setText("")
            binding.group.visibility = View.GONE
            AndroidUtils.hideKeyBoard(binding.editContent)
            binding.editContent.setText("")
        }*/
    }
}






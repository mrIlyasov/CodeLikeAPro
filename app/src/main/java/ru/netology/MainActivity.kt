package ru.netology

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import ru.netology.activity.NewPostActivity
import ru.netology.adapter.OnInteractionListener
import ru.netology.adapter.PostAdapter
import ru.netology.dataClasses.Post
import ru.netology.databinding.ActivityMainBinding
import ru.netology.activity.NewPostResultContract

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult

            viewModel.changeContent(result)
            viewModel.save()
            viewModel.editedDefaultPost()

            binding.list.smoothScrollToPosition(
                (if (viewModel.getSizeOfPosts() > 0) {
                    viewModel.getSizeOfPosts() - 1
                } else {
                    0
                })
            )
        }


        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }


            override fun onEdit(post: Post) {
                viewModel.edit(post)

                newPostLauncher.launch(post.content)

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

            override fun onYoutubeClick(post: Post) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(post.youtubeVideo)
                    )
                )

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


        binding.fab.setOnClickListener() {
            newPostLauncher.launch("")


        }

    }
}






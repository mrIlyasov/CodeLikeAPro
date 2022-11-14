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

    }
}






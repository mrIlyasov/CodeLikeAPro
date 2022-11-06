package ru.netology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.netology.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var post: Post = Post(
        1,
        "Нетология. Университет интернет-профессий",
        "27 октября в 22:19",
        "Привет, это новая Нетология! Когда-то Нетология начиналась с" +
                " интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                "уверенных профессионалов.Привет, это новая Нетология! Когда-то Нетология начиналась с " +
                "интенсива по онлайн-маркетингу. Затем появились курсы по дизайну, аналитике, " +
                "разработке и управлению. Мы растём сами и помогаем расти студентам: от новичков до " +
                "уверенных профессионалов.", 100, 100, 100
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updatePostInfo(binding)


        binding.likeButton.setOnClickListener {
            if (!post.likedByMe) {
                binding.likeButton.setImageResource(R.drawable.liked_icon)
                post.likedByMe = true
                post.likes += 1
            } else {
                binding.likeButton.setImageResource(R.drawable.heart_icon)
                post.likedByMe = false
                post.likes -= 1

            }
            updatePostInfo(binding)
        }

        binding.shareButton.setOnClickListener {
            post.repostsCount += 1
            binding.shareCountTextView.text = rounding(post.repostsCount)

        }

        binding.button.setOnClickListener {
            post.likes += 899
            post.repostsCount += 750
            post.views += 990
            binding.likesCountTextView.text = rounding(this.post.likes)
            binding.shareCountTextView.text = rounding(this.post.repostsCount)
            binding.viewsCountTextView.text = rounding(this.post.views)
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

    fun updatePostInfo(binding: ActivityMainBinding) {
        binding.content.text = post.content
        binding.likesCountTextView.text = rounding(post.likes)
        binding.shareCountTextView.text = rounding(post.repostsCount)
        binding.viewsCountTextView.text = rounding(post.views)
        binding.date.text = post.date
        binding.author.text =  post.authorName
    }



}

class MainActivityViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(MainActivity())
    val uiState: StateFlow<MainActivity> = _uiState
}
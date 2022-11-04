package ru.netology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
        updatePostInfo()


        binding.root.setOnClickListener {
            updatePostInfo()
        }


        binding.avatar.setOnClickListener() {
           binding.content.text = ("Avatar clicked")
        }

        binding.moreButton.setOnClickListener {
            binding.content.text = ("HI")
        }

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
            updatePostInfo()
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
            var symbol3 = value.toString()[2]
            var stringToReturn = "$symbol1" + "$symbol2" + "K"
            return (stringToReturn)
        } else if (value >= 100000 && value < 1_000_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var symbol3 = value.toString()[2]
            var symbol4 = value.toString()[3]
            var stringToReturn = "$symbol1" + "$symbol2" + "$symbol3" + "K"
            return (stringToReturn)
        } else if (value > 1_000_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var stringToReturn = "$symbol1" + "," + "$symbol2" + "M"
            return (stringToReturn)
        } else return value.toString()

    }

    fun updatePostInfo() {
        findViewById<TextView>(R.id.content).text = post.content
        findViewById<TextView>(R.id.likesCountTextView).text = rounding(post.likes)
        findViewById<TextView>(R.id.shareCountTextView).text = rounding(post.repostsCount)
        findViewById<TextView>(R.id.viewsCountTextView).text = rounding(post.views)
    }

}
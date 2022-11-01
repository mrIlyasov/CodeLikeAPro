package ru.netology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

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
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.content).text = post.content
        findViewById<TextView>(R.id.likes_count).text = post.likes.toString()
        findViewById<TextView>(R.id.views_count).text = post.views.toString()
        findViewById<TextView>(R.id.share_count).text = post.repostsCount.toString()
    }


    fun onButtonClick2(View: View) {
        var likesCountTextView: TextView = findViewById(R.id.likes_count)
        var repostCountTextView: TextView = findViewById(R.id.share_count)
        var viewsCountTextView: TextView = findViewById(R.id.views_count)
        this.post.likes += 890
        this.post.repostsCount += 750
        this.post.views += 990
        var likesCount = this.post.likes
        var repostsCount = this.post.repostsCount
        var viewsCount = this.post.views
        likesCountTextView.text = rounding(likesCount)
        repostCountTextView.text = rounding(repostsCount)
        viewsCountTextView.text = rounding(viewsCount)


    }


    fun rounding(value: Int): String {
        if (value > 1100 && value < 10000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var stringToReturn = "$symbol1" + "," + "$symbol2" + "K"
            return (stringToReturn)
        } else if (value >= 10000 && value < 100_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var symbol3 = value.toString()[2]
            var stringToReturn = "$symbol1" + "$symbol2" + "," + "$symbol3" + "K"
            return (stringToReturn)
        } else if (value >= 100000 && value < 1_000_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var symbol3 = value.toString()[2]
            var symbol4 = value.toString()[3]
            var stringToReturn = "$symbol1" + "$symbol2" + "$symbol3" + "," + "$symbol4" + "K"
            return (stringToReturn)
        } else if (value > 1_000_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var stringToReturn = "$symbol1" + "," + "$symbol2" + "M"
            return (stringToReturn)
        } else return value.toString()

    }


}
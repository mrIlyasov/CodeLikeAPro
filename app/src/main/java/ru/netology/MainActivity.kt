package ru.netology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onButtonClick(View: View) {
        val textView: TextView = findViewById(R.id.textView)
        textView.text = "Hello kotlin"
        var scale: Float = 1f
        if (scale < 10) {
            textView.scaleX = textView.scaleX * scale
            textView.scaleY = textView.scaleY * scale
            scale += 1f
        } else {
            textView.scaleX = textView.scaleX * scale
            textView.scaleY = textView.scaleY * scale
            scale -= 2f
        }
    }
}
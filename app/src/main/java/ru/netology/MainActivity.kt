package ru.netology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var scale = Scale
    var counter: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onButtonClick(View: View) {
        val textView: TextView = findViewById(R.id.textView)

        if (this.counter < 2) {
            this.scale.scale += 1
            textView.scaleX = textView.scaleX * this.scale.scale.toFloat()
            textView.scaleY = textView.scaleY * this.scale.scale.toFloat()
            this.counter += 1
        } else {
            this.scale.scale = this.scale.scale * 0.5f
            textView.scaleX = textView.scaleX * this.scale.scale * 0.5f
            textView.scaleY = textView.scaleY * this.scale.scale * 0.5f
            this.counter = 1
        }


    }
}
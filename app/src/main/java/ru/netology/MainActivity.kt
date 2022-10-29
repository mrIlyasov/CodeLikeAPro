package ru.netology

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var boolean: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }



    fun onButtonClick2(View: View) {
        val textView: TextView = findViewById(R.id.content)
        if (this.boolean==false) {
            textView.scaleX = 1f
            textView.scaleY = 1f
            this.boolean = true
        } else {
            textView.scaleX = 4f
            textView.scaleY = 4f
            this.boolean = false
        }


    }
}
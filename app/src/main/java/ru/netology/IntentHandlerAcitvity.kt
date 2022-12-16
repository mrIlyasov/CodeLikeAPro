package ru.netology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import ru.netology.databinding.ActivityIntentHandlerAcitvityBinding
import ru.netology.databinding.ActivityMainBinding

class IntentHandlerAcitvity : AppCompatActivity() {
    val binding: ActivityIntentHandlerAcitvityBinding by lazy {
        ActivityIntentHandlerAcitvityBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        intent?.let {
            if (it.action != Intent.ACTION_SEND) {
                return@let
            }
            var text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()) {
                Snackbar.make(binding.root, R.string.error_empty_content, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok) {
                        finish()
                    }
                     .show()
            } else {
                binding.textView.text=text
            }
        }
    }


}
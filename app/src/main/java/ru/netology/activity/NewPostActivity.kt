package ru.netology.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ru.netology.R
import ru.netology.databinding.ActivityNewPostBinding
import ru.netology.utils.AndroidUtils

class NewPostActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        val text = intent.getStringExtra(Intent.EXTRA_TEXT)
        binding.edit.setText(text)
        setContentView(binding.root)
        binding.edit.requestFocus()


        binding.acceptButton.setOnClickListener {
            val intent = Intent()
            if (binding.edit.text.isNullOrBlank())
                setResult(Activity.RESULT_CANCELED, intent)
            else {
                val content = binding.edit.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)

            }
            finish()
        }

    /*    intent?.let {

            var text = it.getStringExtra(Intent.EXTRA_TEXT)
            binding.edit.setText(text)
        }*/

    }
}

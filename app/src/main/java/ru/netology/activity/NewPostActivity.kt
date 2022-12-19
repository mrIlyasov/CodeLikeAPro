package ru.netology.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.databinding.ActivityNewPostBinding
import ru.netology.utils.AndroidUtils

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.edit.requestFocus()
      //  AndroidUtils.showKeyBoard(binding.edit)
        binding.acceptButton.setOnClickListener {
            val intent = Intent()
            if (binding.edit.text.isNullOrBlank())
                setResult(Activity.RESULT_CANCELED, intent)
            else {
                val content = binding.edit.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content  )
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
      /*     intent?.let {
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
                   binding.edit.setText(text)
               }
           }*/
    }
}
package ru.netology.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import ru.netology.activity.NewPostActivity


object AndroidUtils {
    fun hideKeyBoard(view: View) {
        view.clearFocus()
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            view.applicationWindowToken, 0
        )
        view.clearFocus()
    }

    fun showKeyBoard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        imm.restartInput(view)
        imm.showSoftInput(view.rootView, 0)


        imm.showSoftInput(view, 0)


        /*   val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
           imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)*
           InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
   imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);*/

    }
}
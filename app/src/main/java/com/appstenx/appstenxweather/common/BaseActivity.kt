package com.appstenx.appstenxweather.common

import android.app.Dialog
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.appstenx.appstenxweather.R
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.inputmethod.InputMethodManager


abstract class BaseActivity: AppCompatActivity() {
    fun openLoader():Dialog{
        val dialog=Dialog(this, R.style.transparentLoadingDialog)
        val view=LayoutInflater.from(this).inflate(R.layout.progress_loader,null)
        dialog.apply {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            setContentView(view)
            show()
        }
        val imageViewObjectAnimator = ObjectAnimator.ofFloat(
            view,
            "rotation", 180f
        )
        imageViewObjectAnimator.apply {
            repeatCount=ObjectAnimator.INFINITE;
            repeatMode=ObjectAnimator.RESTART;
            interpolator=AccelerateInterpolator()
            start()
        }

return dialog;
    }
    fun dismissKeyBoard() {
        try {
            val token = findViewById<View>(android.R.id.content).windowToken
            val inputMethodManger =
                getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManger?.hideSoftInputFromWindow(token, 0)
        }
        //This just catches exception to make sure app does not crash this will not impact user
        catch (e: Exception) {
            //This just catches exception to make sure app does not crash
        }
    }
}
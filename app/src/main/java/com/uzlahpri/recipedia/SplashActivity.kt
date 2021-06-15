package com.uzlahpri.recipedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.animation.AnimationUtils
import com.uzlahpri.recipedia.databinding.ActivitySplashBinding
import com.uzlahpri.recipedia.ui.MainActivity

class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 6000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        Handler().postDelayed({
            startActivity(Intent(this, SplashScreen::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}

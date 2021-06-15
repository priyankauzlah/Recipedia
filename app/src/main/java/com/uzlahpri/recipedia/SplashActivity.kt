package com.uzlahpri.recipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import com.uzlahpri.recipedia.databinding.ActivitySplashBinding
import com.uzlahpri.recipedia.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)

    }

    override fun onResume() {
        val animCloud = AnimationUtils.loadAnimation(this, R.anim.splash_code)
        binding.ivSplashLogo.animation = animCloud

        object : CountDownTimer(3600, 200){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                startActivity(MainActivity.getLaunchService(this@SplashActivity))
            }

        }.start()

        super.onResume()
    }
}

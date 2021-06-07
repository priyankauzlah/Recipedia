package com.uzlahpri.recipedia.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.uzlahpri.recipedia.R
import com.uzlahpri.recipedia.databinding.ActivityLoginBinding
import com.uzlahpri.recipedia.databinding.ActivityResetBinding

class ResetActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var resetBinding: ActivityResetBinding

    companion object {
        fun getLaunchService(from: Context) = Intent(from, ResetActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetBinding = ActivityResetBinding.inflate(layoutInflater)

        setContentView(resetBinding.root)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        resetBinding.btnSend.setOnClickListener(this)
        resetBinding.ibBackReset.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.ib_back_reset -> startActivity(LoginActivity.getLaunchService(this))
            R.id.btn_send -> forgotPassword()
        }
    }

    private fun forgotPassword() {
        val email = resetBinding.etResetEmail.text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
        } else {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Check Email to reset password", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(LoginActivity.getLaunchService(this)))
                } else {
                    Toast.makeText(this, "Failed to reset password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
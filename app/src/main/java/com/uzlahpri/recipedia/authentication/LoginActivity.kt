package com.uzlahpri.recipedia.authentication

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.uzlahpri.recipedia.R
import com.uzlahpri.recipedia.databinding.ActivityLoginBinding
import com.uzlahpri.recipedia.ui.MainActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var loginBinding: ActivityLoginBinding

    companion object {
        fun getLaunchService(from: Context) = Intent(from, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //add
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)

        //ubah
        setContentView(loginBinding.root)
        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        //login binding dulu
        loginBinding.btnLogin.setOnClickListener(this)
        loginBinding.tvLoginForgot.setOnClickListener(this)
        loginBinding.tvLoginCreate.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.tv_login_forgot -> startActivity(Intent(ResetActivity.getLaunchService(this)))
            R.id.tv_login_create -> startActivity(Intent(CreateActivity.getLaunchService(this)))
            R.id.btn_login -> loginEmailPass()
        }
    }

    private fun loginEmailPass() {
        val email = loginBinding.etLoginEmail.text.toString()
        val password = loginBinding.etLoginPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Insert a complete data", Toast.LENGTH_SHORT).show()
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    startActivity(MainActivity.getLaunchService(this))
                    return@addOnCompleteListener
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                val progress = ProgressDialog(this, R.style.Theme_AppCompat_Light_Dialog)
                progress.hide()
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }

    }
}
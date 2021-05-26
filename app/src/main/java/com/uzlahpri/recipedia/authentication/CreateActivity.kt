package com.uzlahpri.recipedia.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.uzlahpri.recipedia.R
import com.uzlahpri.recipedia.databinding.ActivityCreateBinding
import com.uzlahpri.recipedia.ui.MainActivity

class CreateActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private lateinit var createBinding: ActivityCreateBinding
    private var firebaseUserId: String = ""

    companion object {
        fun getLaunchService(from: Context) =
            Intent(from, CreateActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        supportActionBar?.hide()
        createBinding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(createBinding.root)

        createBinding.btnCreate.setOnClickListener(this)
        createBinding.tvCreateLogin.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btn_create -> signUpUser()
            R.id.tv_create_login -> startActivity(LoginActivity.getLaunchService(this))
        }
    }

    private fun signUpUser() {
        val username: String = createBinding.etCreateUsername.text.toString()
        val email: String = createBinding.etCreateEmail.text.toString()
        val password: String = createBinding.etCreatePassword.text.toString()

        if (username == "") {
            Toast.makeText(this, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
        } else if (email == "") {
            Toast.makeText(this, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
        } else if (password == "") {
            Toast.makeText(this, getString(R.string.error_text), Toast.LENGTH_SHORT).show()
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers =
                        FirebaseDatabase.getInstance().reference.child(getString(R.string.text_user))
                            .child(firebaseUserId)
                    val userHashMap = HashMap<String, Any>()

                    userHashMap["uid"] = firebaseUserId
                    userHashMap["username"] = username
                    userHashMap["email"] = email

                    refUsers.updateChildren(userHashMap).addOnCompleteListener { it ->
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.error_create) + it.exception!!.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
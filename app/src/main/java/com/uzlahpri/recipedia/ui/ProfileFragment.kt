package com.uzlahpri.recipedia.ui


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.uzlahpri.recipedia.R
import com.uzlahpri.recipedia.authentication.LoginActivity
import com.uzlahpri.recipedia.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(), View.OnClickListener {

    var firebaseUser: FirebaseUser? = null
    private lateinit var profileFragmentBinding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseUser = FirebaseAuth.getInstance().currentUser
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileFragmentBinding = FragmentProfileBinding.inflate(layoutInflater)
        return profileFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileFragmentBinding.btnLogout.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when(p0.id) {
            R.id.btn_logout -> logOut()
        }
    }

    private fun logOut() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        FirebaseAuth.getInstance().signOut()
    }
}
package com.uzlahpri.recipedia.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uzlahpri.recipedia.R

class JokesFragment : Fragment() {

    private lateinit var jokesViewModel: JokesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        jokesViewModel =
            ViewModelProvider(this).get(JokesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_jokes, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        jokesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
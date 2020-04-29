package com.example.liflow.presentation.ui.post.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.liflow.R
import com.example.liflow.presentation.ui.post.viewmodel.NewPostViewModel

class NewPostFragment : Fragment() {

    private lateinit var newPostViewModel: NewPostViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newPostViewModel =
            ViewModelProviders.of(this).get(NewPostViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_post, container, false)
        val textView: TextView = root.findViewById(R.id.fragment_new_post_textView_title)
        newPostViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}
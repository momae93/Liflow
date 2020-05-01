package com.example.liflow.presentation.ui.post.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.liflow.R
import com.example.liflow.presentation.ui.post.viewmodel.PostDetailsViewModel

class PostDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PostDetailsFragment()
    }

    private lateinit var viewModel: PostDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

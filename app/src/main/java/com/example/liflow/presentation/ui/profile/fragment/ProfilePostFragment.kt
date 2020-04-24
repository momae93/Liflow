package com.example.liflow.presentation.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.liflow.R

class ProfilePostFragment : Fragment() {

    companion object {
        fun newInstance() = ProfilePostFragment()
    }

    private lateinit var viewModel: ProfilePostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfilePostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

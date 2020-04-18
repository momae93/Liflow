package com.example.liflow.presentation.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.liflow.BR

import com.example.liflow.R
import com.example.liflow.databinding.FragmentProfileBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.login.LoginViewModel
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    companion object {
        fun newInstance() = ProfileFragment()
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var profileViewModel: ProfileViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): ProfileViewModel {
        profileViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ProfileViewModel::class.java)
        return profileViewModel
    }

    override fun initObservers() {
    }
}

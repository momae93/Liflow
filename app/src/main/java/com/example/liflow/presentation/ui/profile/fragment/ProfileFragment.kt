package com.example.liflow.presentation.ui.profile.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.FragmentProfileBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.profile.viewmodel.ProfileViewModel
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    companion object {
        fun newInstance() =
            ProfileFragment()
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: ProfileViewModel
    private lateinit var viewBinding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserProfileDetails("fOlmNZnpfP")
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): ProfileViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ProfileViewModel::class.java)
        return viewModel
    }

    override fun initObservers() {}
}

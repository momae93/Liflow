package com.example.liflow.presentation.ui.profile.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.FragmentProfileBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.profile.viewmodel.ProfileViewModel
import javax.inject.Inject
import javax.inject.Named

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel, IProfileNavigator>(), IProfileNavigator {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserProfileDetails()
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

    override fun navigateToPostFragment(isLikedPostsCategory: Boolean) {
        val action = ProfileFragmentDirections
            .actionNavigationProfileToNavigationProfilePost(isLikedPostsCategory)
        findNavController().navigate(action)
    }

    override fun getNavigator(): IProfileNavigator = this
}

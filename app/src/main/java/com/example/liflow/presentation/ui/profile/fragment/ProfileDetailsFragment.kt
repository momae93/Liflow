package com.example.liflow.presentation.ui.profile.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.FragmentProfileDetailsBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.post.fragment.PostDetailsFragmentArgs
import com.example.liflow.presentation.ui.profile.viewmodel.ProfileDetailsViewModel
import javax.inject.Inject

class ProfileDetailsFragment : BaseFragment<FragmentProfileDetailsBinding, ProfileDetailsViewModel, IProfileDetailsNavigator>(), IProfileDetailsNavigator {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: ProfileDetailsViewModel

    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val safeArgs = ProfileDetailsFragmentArgs.fromBundle(it)
            userId = safeArgs.userId
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId?.let {
            viewModel.getProfileDetails(it)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile_details
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): ProfileDetailsViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ProfileDetailsViewModel::class.java)
        return viewModel
    }

    override fun navigateToWrittenPostsFragment() {
        val action = CurrentCurrentProfileFragmentDirections
            .actionNavigationProfileToNavigationProfilePost()
        findNavController().navigate(action)
    }

    override fun navigateToBadgesFragment() {
    }

    override fun getNavigator(): IProfileDetailsNavigator = this
}

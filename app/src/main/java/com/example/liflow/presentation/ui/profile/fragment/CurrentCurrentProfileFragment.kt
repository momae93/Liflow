package com.example.liflow.presentation.ui.profile.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.FragmentCurrentProfileBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.login.LoginActivity
import com.example.liflow.presentation.ui.profile.viewmodel.CurrentProfileViewModel
import javax.inject.Inject

class CurrentCurrentProfileFragment : BaseFragment<FragmentCurrentProfileBinding, CurrentProfileViewModel, ICurrentProfileNavigator>(), ICurrentProfileNavigator {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModelCurrent: CurrentProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelCurrent.getUserProfileDetails()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_current_profile
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): CurrentProfileViewModel {
        viewModelCurrent = ViewModelProviders.of(this, viewModelProviderFactory).get(CurrentProfileViewModel::class.java)
        return viewModelCurrent
    }

    override fun navigateToWrittenPostsFragment() {
        val action = CurrentCurrentProfileFragmentDirections
            .actionNavigationProfileToNavigationProfilePost()
        findNavController().navigate(action)
    }

    override fun navigateToBadgesFragment() {
    }

    override fun navigateToLoginActivity() {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        this.activity?.finish()
    }

    override fun getNavigator(): ICurrentProfileNavigator = this
}

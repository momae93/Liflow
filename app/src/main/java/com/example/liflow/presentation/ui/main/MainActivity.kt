package com.example.liflow.presentation.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.ActivityMainBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject
import javax.inject.Named


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel, IMainNavigator>(), IMainNavigator {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewBinding: ActivityMainBinding

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    @JvmField
    @Named("sessionToken")
    internal var sessionToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = getViewBinding() as ActivityMainBinding

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): MainViewModel {
        mainViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(MainViewModel::class.java)
        return mainViewModel
    }

    override fun initObservers() {}

    override fun getNavigator(): IMainNavigator = this
}

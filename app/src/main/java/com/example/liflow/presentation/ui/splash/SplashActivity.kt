package com.example.liflow.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.ActivitySplashBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseActivity
import com.example.liflow.presentation.ui.login.LoginActivity
import com.example.liflow.presentation.ui.main.MainActivity
import javax.inject.Inject
import javax.inject.Named

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel, ISplashNavigator>(), ISplashNavigator {
    private lateinit var viewModel: SplashViewModel

    private lateinit var viewBinding: ActivitySplashBinding

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    @JvmField
    @Named("sessionToken")
    internal var sessionToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (sessionToken === null) {
            navigateToLoginActivity()
        } else {
            navigateToMainActivity()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): SplashViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(SplashViewModel::class.java)
        return viewModel
    }

    override fun initObservers() {}

    override fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun getNavigator(): ISplashNavigator = this
}

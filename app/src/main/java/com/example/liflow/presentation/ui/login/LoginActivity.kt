package com.example.liflow.presentation.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.liflow.BR
import com.example.liflow.presentation.ui.main.MainActivity
import com.example.liflow.R
import com.example.liflow.databinding.ActivityLoginBinding
import com.example.liflow.presentation.models.State
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseActivity
import com.example.liflow.presentation.ui.register.view.RegisterActivity
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel, ILoginNavigator>(), ILoginNavigator {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var viewBinding: ActivityLoginBinding

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = getViewBinding() as ActivityLoginBinding
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModel(): LoginViewModel {
        loginViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(LoginViewModel::class.java)
        return loginViewModel
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }
    override fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun navigateToRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun initObservers() {
        observeLogin()
    }

    override fun getNavigator(): ILoginNavigator = this

    private fun observeLogin() {
        loginViewModel.loginStateLiveData.observe(this, Observer { state ->
            when(state) {
                is State.Loading -> Toast.makeText(this, "Loading ...", Toast.LENGTH_SHORT).show()
                is State.Success -> {
                    loginViewModel.saveSessionTokenLocally(state.data)
                    navigateToMainActivity()
                }
                is State.Error -> Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}

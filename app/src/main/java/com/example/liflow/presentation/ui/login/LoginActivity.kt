package com.example.liflow.presentation.ui.login

import android.content.Intent
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
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), ILoginNavigator {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var viewBinding: ActivityLoginBinding

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

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

    override fun navigateToMainActivity(sessionToken: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("SESSION_TOKEN", sessionToken)
        startActivity(intent)
        finish()
    }

    override fun initObservers() {
        observeLogin()
    }

    private fun observeLogin() {
        loginViewModel.loginStateLiveData.observe(this, Observer { state ->
            when(state) {
                is State.Loading -> Toast.makeText(this, "Loading ...", Toast.LENGTH_SHORT).show()
                is State.Success -> navigateToMainActivity(state.data)
                is State.Error -> Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}

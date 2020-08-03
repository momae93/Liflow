package com.example.liflow.presentation.ui.register.view

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.ActivityRegisterBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseActivity
import com.example.liflow.presentation.ui.main.MainActivity
import com.example.liflow.presentation.ui.register.viewmodel.RegisterViewModel
import java.util.*
import javax.inject.Inject

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel, IRegisterNavigator>(),
    IRegisterNavigator {

    private lateinit var viewModel: RegisterViewModel

    private lateinit var viewBinding: ActivityRegisterBinding

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = getViewBinding() as ActivityRegisterBinding
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun getViewModel(): RegisterViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(RegisterViewModel::class.java)
        return viewModel
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }
    override fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun initObservers() {}

    override fun getNavigator(): IRegisterNavigator = this

    override fun openBirthDateDialog() {
        val calendar = Calendar.getInstance()
        val calendarYear = calendar.get(Calendar.YEAR)
        val calendarMonth = calendar.get(Calendar.MONTH)
        val calendarDay = calendar.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val formattedMonth =
                    if ((monthOfYear + 1) < 10) "0${monthOfYear + 1}" else "${monthOfYear + 1}"
                val formattedDay = if ((dayOfMonth) < 10) "0$dayOfMonth" else "$dayOfMonth"
                val birthDateText = "$year-$formattedMonth-$formattedDay"

                viewModel.registerForm.birthdate.fieldValue.value = birthDateText
            }, calendarYear, calendarMonth, calendarDay
        )
        dialog.show()
    }
}

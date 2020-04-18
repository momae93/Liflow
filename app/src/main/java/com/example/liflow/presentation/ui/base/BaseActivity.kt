package com.example.liflow.presentation.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection

abstract class BaseActivity<VIEW_DATA_BINDING: ViewDataBinding, VIEW_MODEL: ViewModel>:
    AppCompatActivity() {
    private lateinit var viewBinding: VIEW_DATA_BINDING
    private lateinit var viewModel: VIEW_MODEL

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performViewBinding()
        initObservers()
    }

    abstract fun getLayoutId(): Int

    abstract fun getViewModelBindingVariable(): Int

    abstract fun getViewModel(): VIEW_MODEL

    abstract fun initObservers()

    fun getViewBinding(): ViewDataBinding = viewBinding

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performViewBinding() {
        viewBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewBinding.setVariable(getViewModelBindingVariable(), getViewModel())
    }
}
package com.example.liflow.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.liflow.R
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<VIEW_DATA_BINDING: ViewDataBinding, VIEW_MODEL: ViewModel>: Fragment() {
    private lateinit var viewBinding: VIEW_DATA_BINDING
    private lateinit var viewModel: VIEW_MODEL

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        initObservers()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        performViewBinding(layoutInflater, container)
        return inflater.inflate(getLayoutId(), container, false)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModelBindingVariable(): Int

    abstract fun getViewModel(): VIEW_MODEL

    abstract fun initObservers()

    fun getViewBinding(): ViewDataBinding = viewBinding

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    private fun performViewBinding(layoutInflater: LayoutInflater, viewGroup: ViewGroup?) {
        viewBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), viewGroup, false)
        viewBinding.setVariable(getViewModelBindingVariable(), getViewModel())
    }
}
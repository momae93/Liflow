package com.example.liflow.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.liflow.R
import com.example.liflow.presentation.ui.profile.viewmodel.ProfileViewModel
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<VIEW_DATA_BINDING: ViewDataBinding, VIEW_MODEL: BaseViewModel>: Fragment() {
    private lateinit var viewBinding: VIEW_DATA_BINDING
    private lateinit var viewModel: VIEW_MODEL

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =  getViewModel()
        viewBinding = performViewBinding(layoutInflater, container)
        initInternalObservers()
        initObservers()
        return viewBinding.root
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModelBindingVariable(): Int

    abstract fun getViewModel(): VIEW_MODEL

    abstract fun initObservers()

    fun getViewBinding(): ViewDataBinding = viewBinding

    open fun observeOnLoad() {
        viewModel.isLoading.observe(this, Observer {
            Toast.makeText(context, "Loading ...", Toast.LENGTH_SHORT).show()
        })
    }

    open fun observeOnError() {
        viewModel.errorHandler.observe(this, Observer {
            Toast.makeText(context, "Oops an error occurred ...", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initInternalObservers() {
        observeOnLoad()
        observeOnError()
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    private fun performViewBinding(layoutInflater: LayoutInflater, viewGroup: ViewGroup?): VIEW_DATA_BINDING {
        val viewBinding: VIEW_DATA_BINDING = DataBindingUtil.inflate(layoutInflater, getLayoutId(), viewGroup, false)
        viewBinding.lifecycleOwner = this.viewLifecycleOwner
        viewBinding.setVariable(getViewModelBindingVariable(), viewModel)
        viewBinding.executePendingBindings()

        return viewBinding
    }
}
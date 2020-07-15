package com.example.liflow.presentation.ui.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<VIEW_DATA_BINDING: ViewDataBinding, VIEW_MODEL: BaseViewModel<NAVIGATOR>, NAVIGATOR: IBaseNavigator>: Fragment() {
    private lateinit var baseViewBinding: VIEW_DATA_BINDING
    private lateinit var baseViewModel: VIEW_MODEL

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        baseViewModel = getViewModel()
        baseViewModel.setNavigator(getNavigator())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseViewBinding = performViewBinding(layoutInflater, container)
        initInternalObservers()
        initObservers()
        return baseViewBinding.root
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModelBindingVariable(): Int

    abstract fun getViewModel(): VIEW_MODEL

    abstract fun getNavigator(): NAVIGATOR

    fun getViewBinding(): ViewDataBinding = baseViewBinding

    open fun initObservers() {}

    open fun observeOnLoad() {
        baseViewModel.isLoading.observe(this, Observer {
            Toast.makeText(context, "Loading ...", Toast.LENGTH_SHORT).show()
        })
    }

    open fun observeOnError() {
        baseViewModel.errorHandler.observe(this, Observer {
            Toast.makeText(context, "Oops an error occurred ...", Toast.LENGTH_SHORT).show()
        })
    }

    fun closeKeyBoard() {
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.let {
            it.hideSoftInputFromWindow(view?.windowToken, 0)
        }
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
        viewBinding.setVariable(getViewModelBindingVariable(), baseViewModel)
        viewBinding.executePendingBindings()

        return viewBinding
    }
}
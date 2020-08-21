package com.example.liflow.presentation.ui.post.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.FragmentNewPostBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseDialogFragment
import com.example.liflow.presentation.ui.post.adapter.NewPostCategoryRecyclerAdapter
import com.example.liflow.presentation.ui.post.viewmodel.NewPostViewModel
import javax.inject.Inject


class NewPostFragment : BaseDialogFragment<FragmentNewPostBinding, NewPostViewModel, INewPostNavigator>(),
    INewPostNavigator,
    NewPostCategoryRecyclerAdapter.OnCategoryListener {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: NewPostViewModel
    private lateinit var viewBinding: FragmentNewPostBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding() as FragmentNewPostBinding
        viewModel.getAllCategory()

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    Toast.makeText(context, "Back pressed !", Toast.LENGTH_SHORT).show()
                    viewModel.handlePreviousStep()
                }
            }

        activity?.onBackPressedDispatcher?.addCallback(this, callback)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_new_post
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): NewPostViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(NewPostViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): INewPostNavigator = this

    override fun onCategoryClick(categoryId: Int) {
        viewModel.onCategoryClick(categoryId)
    }

    override fun initObservers() {
        super.initObservers()
        observeCategories()
    }

    private fun observeCategories() {
        viewModel.categories.observe(this, Observer { lists ->
            viewBinding.fragmentNewPostRecyclerViewCategory.layoutManager = LinearLayoutManager(context)
            viewBinding.fragmentNewPostRecyclerViewCategory.adapter = NewPostCategoryRecyclerAdapter(lists, this)
        })
    }

    override fun navigateBackToFragment() {
        this.dismiss()
    }
}
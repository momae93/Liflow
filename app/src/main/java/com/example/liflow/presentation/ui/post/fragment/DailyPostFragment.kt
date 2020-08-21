package com.example.liflow.presentation.ui.post.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.FragmentDailyPostBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.post.adapter.DailyRandomPostRecyclerAdapter
import com.example.liflow.presentation.ui.post.viewmodel.DailyPostViewModel
import com.example.liflow.presentation.ui.profile.fragment.ProfilePostFragmentDirections
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import javax.inject.Inject
import javax.inject.Named

class DailyPostFragment :
    BaseFragment<FragmentDailyPostBinding, DailyPostViewModel, IDailyPostNavigator>(),
    IDailyPostNavigator,
    DailyRandomPostRecyclerAdapter.OnPostListener,
    CardStackListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: DailyPostViewModel
    private lateinit var viewBinding: FragmentDailyPostBinding
    private val manager by lazy { CardStackLayoutManager(context, this) }
    private val recyclerAdapter by lazy { DailyRandomPostRecyclerAdapter(onPostListener = this)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding() as FragmentDailyPostBinding
        viewModel.getRandomDailyPost()
        viewBinding.fragmentDailyPostCardStackViewContainer.adapter = recyclerAdapter
//        viewBinding.fragmentDailyPostCardStackViewContainer.layoutManager = manager
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_daily_post
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): DailyPostViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory)
            .get(DailyPostViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): IDailyPostNavigator = this
    override fun navigateToPostDetails(postDetails: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardSwiped(direction: Direction?) {
        if (manager.topPosition == recyclerAdapter.itemCount) {
            viewModel.getRandomDailyPost()
        }
    }

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardRewound() {}

    override fun onPostThumbnailClick(postId: Int) {
        val action = DailyPostFragmentDirections
            .actionNavigationDailyPostToNavigationPostDetails(postId)
        findNavController().navigate(action)
    }

    override fun observeOnLoad() {}

    override fun initObservers() {
        super.initObservers()
        observeGetLikedPosts()
    }

    private fun observeGetLikedPosts() {
        viewModel.dailyRandomPosts.observe(this, Observer { list ->
            recyclerAdapter.updateList(list)
        })
    }
}

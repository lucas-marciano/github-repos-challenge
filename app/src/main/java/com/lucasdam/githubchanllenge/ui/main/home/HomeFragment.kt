package com.lucasdam.githubchanllenge.ui.main.home

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasdam.githubchanllenge.databinding.HomeLayoutBinding
import com.lucasdam.githubchanllenge.mvvm.BaseFragment
import com.lucasdam.githubchanllenge.ui.main.MainContract
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * @author Lucas Marciano on 03/01/21.
 */

class HomeFragment :
    BaseFragment<MainContract.ActivityView, HomeContract.ViewModel, HomeLayoutBinding>() {

    override val viewModel: HomeViewModelImpl by viewModel { parametersOf(this) }
    override fun bindView() = initBinding(HomeLayoutBinding.inflate(layoutInflater), this)
    override val module = HomeModule.module

    private val homeAdapter: HomeAdapter by inject()

    override fun initialize() {
        initializeElements()
        viewModel.input.getRepositories(2)
    }

    override fun bindViewModels() {
        viewModel.output.onFetchRepositories.observe(this, Observer { homeAdapter.list = it })
    }

    private fun initializeElements() {
        bind.homeRvItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter.apply {
                callback = viewModel.input::routeToImageFragment
            }
        }
    }

    private fun buildScrollListenerRecyclerView() {

    }


}

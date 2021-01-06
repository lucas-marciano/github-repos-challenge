package com.lucasdam.githubchanllenge.ui.main.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasdam.githubchanllenge.databinding.HomeLayoutBinding
import com.lucasdam.githubchanllenge.mvvm.BaseFragment
import com.lucasdam.githubchanllenge.shared.extensions.onScrollToEnd
import com.lucasdam.githubchanllenge.ui.main.MainContract
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import com.lucasdam.githubchanllenge.shared.extensions.observer
import com.lucasdam.githubchanllenge.shared.model.view.Repository

private const val KEY_LIST = "KEY_LIST"

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
        viewModel.input.getRepositories()
    }

    override fun bindViewModels() {
        viewModel.output.onFetchRepositories.observer(viewLifecycleOwner) {
            if (savedInstanceState != null) {
                homeAdapter.list =
                    savedInstanceState?.getParcelableArrayList<Repository>(KEY_LIST)!!
                        .toMutableList()
            } else {
                homeAdapter.list = it.toMutableList()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEY_LIST, ArrayList(homeAdapter.list))
    }

    private fun initializeElements() {
        bind.homeRvItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter.apply {
                callback = viewModel.input::routeToImageFragment
            }
            onScrollToEnd {
                clearState()
                homeAdapter.plusPage()
                viewModel.input.getRepositories(homeAdapter.page)
            }
        }
    }
}

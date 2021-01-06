package com.lucasdam.githubchanllenge.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.lucasdam.githubchanllenge.mvvm.BaseViewModel
import com.lucasdam.githubchanllenge.shared.di.DisposeBag
import com.lucasdam.githubchanllenge.shared.extensions.call
import com.lucasdam.githubchanllenge.shared.model.view.Repository
import com.lucasdam.githubchanllenge.shared.network.ThrowableHandler
import com.lucasdam.githubchanllenge.shared.provider.SchedulerProvider
import com.lucasdam.githubchanllenge.ui.main.MainContract

/**
 * @author Lucas Marciano on 03/01/21.
 */

class HomeViewModelImpl(
    interactor: HomeContract.Interactor,
    disposeBag: DisposeBag,
    schedulerProvider: SchedulerProvider,
    throwableHandler: ThrowableHandler,
    private val router: MainContract.Router
) : BaseViewModel<HomeContract.Interactor>(
    interactor,
    disposeBag,
    schedulerProvider,
    throwableHandler
), HomeContract.ViewModel, HomeContract.ViewModel.Input, HomeContract.ViewModel.Output {

    override val onFetchRepositories: MutableLiveData<List<Repository>> = MutableLiveData()

    override fun getRepositories() {
        disposeBag!!.add(
            interactor.fetchAllRepositories()
                .compose(observeOnUiAfterResult())
                .doOnSubscribe { showLoading.call() }
                .doFinally { hideLoading.call() }
                .subscribe({ onFetchRepositories.value = it }, this::showError)
        )
    }

    override fun routeToImageFragment(repository: Repository) {
        router.routeToImageFragment(repository)
    }

    override val input: HomeContract.ViewModel.Input = this
    override val output: HomeContract.ViewModel.Output = this
}

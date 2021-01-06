package com.lucasdam.githubchanllenge.ui.main.home

import androidx.lifecycle.LiveData
import com.lucasdam.githubchanllenge.mvvm.BaseContract
import com.lucasdam.githubchanllenge.shared.model.view.Repository
import com.lucasdam.githubchanllenge.shared.usecases.FetchAllRepositoriesUseCase

/**
 * @author Lucas Marciano on 03/01/21.
 */

interface HomeContract {

    interface ViewModel : BaseContract.ViewModel {
        val input: Input
        val output: Output

        interface Input {
            fun getRepositories(page: Int = 1)
            fun routeToImageFragment(repository: Repository)
        }

        interface Output {
            val onFetchRepositories: LiveData<List<Repository>>
        }
    }

    interface Interactor : BaseContract.Interactor, FetchAllRepositoriesUseCase
}

package com.lucasdam.githubchanllenge.shared.usecases

import com.lucasdam.githubchanllenge.mvvm.BaseContract
import com.lucasdam.githubchanllenge.shared.model.view.Repository
import com.lucasdam.githubchanllenge.shared.services.MainService
import io.reactivex.Single

/**
 * @author Lucas Marciano on 05/04/20.
 */

interface FetchAllRepositoriesUseCase : BaseContract.UseCase {
    fun fetchAllRepositories(page: Int): Single<List<Repository>>
}

class FetchAllRepositoriesUseCaseImpl(
    private val mainService: MainService
) : FetchAllRepositoriesUseCase {

    override fun fetchAllRepositories(page: Int) =
        mainService.fetchAllRepositories(page).map { it.items.map { item -> Repository(item) } }
}

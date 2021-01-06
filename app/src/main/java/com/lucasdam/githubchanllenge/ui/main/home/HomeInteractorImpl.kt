package com.lucasdam.githubchanllenge.ui.main.home

import com.lucasdam.githubchanllenge.shared.usecases.FetchAllRepositoriesUseCase

/**
 * @author Lucas Marciano on 03/01/21.
 */

class HomeInteractorImpl(
    private val fetchAllRepositoriesUseCase: FetchAllRepositoriesUseCase
) : HomeContract.Interactor {

    override fun fetchAllRepositories(page: Int) = fetchAllRepositoriesUseCase.fetchAllRepositories(page)
}

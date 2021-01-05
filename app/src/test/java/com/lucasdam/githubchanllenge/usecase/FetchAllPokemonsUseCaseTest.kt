package com.lucasdam.githubchanllenge.usecase

import com.lucasdam.githubchanllenge.shared.UseCaseTest
import com.lucasdam.githubchanllenge.shared.model.domain.response.RepositoryItemResponse
import com.lucasdam.githubchanllenge.shared.model.domain.response.RepositoryResponse
import com.lucasdam.githubchanllenge.shared.model.view.Repository
import com.lucasdam.githubchanllenge.shared.services.MainService
import com.lucasdam.githubchanllenge.shared.usecases.FetchAllRepositoriesUseCase
import com.lucasdam.githubchanllenge.shared.usecases.FetchAllRepositoriesUseCaseImpl
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

/**
 * @author Lucas Marciano on 07/04/20.
 */

class FetchAllRepositoriesUseCaseTest : UseCaseTest<FetchAllRepositoriesUseCase>() {

    override lateinit var useCase: FetchAllRepositoriesUseCase

    private val mainService = mockk<MainService>()

    override fun initialize() {
        useCase = FetchAllRepositoriesUseCaseImpl(mainService)
    }

    @Test
    fun `fetch all pokemons successfully`() {
        val response = RepositoryResponse(
            listOf(
                RepositoryItemResponse("a", "aa"),
                RepositoryItemResponse("b", "bb")
            )
        )

        val expected = listOf(
            Repository("a", "aa"),
            Repository("b", "bb")
        )

        every { mainService.fetchAllRepositories() } returns Single.just(response)

        useCase
            .fetchAllRepositories()
            .test()
            .assertResult(expected)

        verify { mainService.fetchAllRepositories() }

        confirmVerified(mainService)
    }
}

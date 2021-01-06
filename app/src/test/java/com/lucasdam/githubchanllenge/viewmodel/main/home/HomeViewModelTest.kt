package com.lucasdam.githubchanllenge.viewmodel.main.home

import com.lucasdam.githubchanllenge.shared.model.view.Repository
import com.lucasdam.githubchanllenge.ui.main.MainContract
import com.lucasdam.githubchanllenge.ui.main.home.HomeContract
import com.lucasdam.githubchanllenge.ui.main.home.HomeViewModelImpl
import com.lucasdam.githubchanllenge.shared.ViewModelTest
import com.jraska.livedata.TestObserver
import com.lucasdam.githubchanllenge.shared.model.view.Owner
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

/**
 * @author Lucas Marciano on 05/04/20.
 */

class HomeViewModelTest : ViewModelTest<HomeContract.ViewModel>() {

    override lateinit var viewModel: HomeContract.ViewModel

    private val interactor = mockk<HomeContract.Interactor>()
    private val router = mockk<MainContract.Router>()
    private val onFetchRepositoriesObserver: TestObserver<List<Repository>> = TestObserver.create()
    private val showLoadingObserver: TestObserver<Void> = TestObserver.create()
    private val hideLoadingObserver: TestObserver<Void> = TestObserver.create()

    override fun initialize() {
        viewModel = HomeViewModelImpl(
            interactor,
            disposeBag,
            schedulerProvider,
            throwableHandler,
            router
        )
    }

    @Test
    fun `fetch repository successfully`() {
        every { interactor.fetchAllRepositories() } returns Single.just(getRepositories())

        viewModel.output.onFetchRepositories.observeForever(onFetchRepositoriesObserver)
        viewModel.showLoading.observeForever(showLoadingObserver)
        viewModel.hideLoading.observeForever(hideLoadingObserver)

        viewModel.input.getRepositories()
        testScheduler.triggerActions()

        verify { interactor.fetchAllRepositories() }

        showLoadingObserver.assertHasValue()
        hideLoadingObserver.assertHasValue()
        onFetchRepositoriesObserver.assertValue(getRepositories())

        confirmVerified(interactor)
    }

    private fun getRepositories() =
        listOf(
            Repository("1", 0, 0, Owner("", "")),
            Repository("2", 0, 0, Owner("", ""))
        )
}

package com.lucasdam.githubchanllenge.shared

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lucasdam.githubchanllenge.mvvm.BaseContract
import com.lucasdam.githubchanllenge.shared.di.DisposeBag
import com.lucasdam.githubchanllenge.shared.network.ThrowableHandler
import com.lucasdam.githubchanllenge.shared.provider.SchedulerProvider
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule

/**
 * @author Lucas Marciano on 05/04/20.
 */

abstract class ViewModelTest<ViewModelType : BaseContract.ViewModel> {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    protected lateinit var disposeBag: DisposeBag
    protected lateinit var testScheduler: TestScheduler
    protected lateinit var schedulerProvider: SchedulerProvider
    protected lateinit var throwableHandler: ThrowableHandler

    abstract val viewModel: ViewModelType

    @Before
    fun setupTest() {
        disposeBag = DisposeBag()
        testScheduler = TestScheduler()
        schedulerProvider = TestSchedulerProvider(testScheduler)
        throwableHandler = TestThrowableHandler()

        initialize()
    }

    protected abstract fun initialize()
}

package com.lucasdam.githubchanllenge.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucasdam.githubchanllenge.shared.di.DisposeBag
import com.lucasdam.githubchanllenge.shared.network.ThrowableHandler
import com.lucasdam.githubchanllenge.shared.provider.SchedulerProvider
import com.lucasdam.githubchanllenge.shared.provider.StringProvider
import io.reactivex.CompletableTransformer
import io.reactivex.CompletableSource
import io.reactivex.Completable
import io.reactivex.SingleTransformer
import io.reactivex.SingleSource
import io.reactivex.Single

/**
 * @author Lucas Marciano on 05/04/20.
 */

abstract class BaseViewModel<InteractorType>(
    protected val interactor: InteractorType,
    protected val disposeBag: DisposeBag? = null,
    protected val schedulerProvider: SchedulerProvider? = null,
    private val throwableHandler: ThrowableHandler? = null,
    private val stringProvider: StringProvider? = null
) : ViewModel(), BaseContract.ViewModel where InteractorType : BaseContract.Interactor {

    override val showLoading: MutableLiveData<Void> = MutableLiveData()
    override val hideLoading: MutableLiveData<Void> = MutableLiveData()
    override val onError: MutableLiveData<String> = MutableLiveData()

    init {
        disposeBag?.clear()
    }

    protected fun showError(throwable: Throwable) {
        onError.value = throwableHandler?.getExceptionMessage(throwable)?.message
    }

    override fun onCleared() {
        disposeBag?.dispose()
        super.onCleared()
    }

    protected fun <T> observeOnUiAfterResult() = UIThreadResultTransformer<T>()

    protected fun observeOnUiAfterCompletion() = UIThreadCompletionTransformer()

    inner class UIThreadCompletionTransformer : CompletableTransformer {
        override fun apply(upstream: Completable): CompletableSource =
            upstream
                .subscribeOn(schedulerProvider?.io())
                .observeOn(schedulerProvider?.ui())
    }

    inner class UIThreadResultTransformer<T> : SingleTransformer<T, T> {
        override fun apply(upstream: Single<T>): SingleSource<T> =
            upstream
                .subscribeOn(schedulerProvider?.io())
                .observeOn(schedulerProvider?.ui())
    }
}

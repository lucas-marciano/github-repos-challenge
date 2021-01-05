package com.lucasdam.githubchanllenge.mvvm

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData

/**
 * @author Lucas Marciano on 05/04/20.
 */

interface BaseContract {

    interface ActionView {
        fun showLoading()
        fun hideLoading()
        fun getString(@StringRes stringRes: Int): String
        fun showError(error: String)
        fun showNotImplementedAlert()
    }

    interface ActivityView : ActionView {
        fun showBackButton()
        fun hideBackButton()
        fun closeKeyboard()
        fun openKeyboard()
    }

    interface ViewModel {
        val showLoading: MutableLiveData<Void>
        val hideLoading: MutableLiveData<Void>
        val onError: MutableLiveData<String>
    }

    interface Interactor

    interface Router {
        fun onBackPressed()
        fun finishActivity()
    }

    interface UseCase
}

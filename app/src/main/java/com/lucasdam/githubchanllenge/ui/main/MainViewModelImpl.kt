package com.lucasdam.githubchanllenge.ui.main

import com.lucasdam.githubchanllenge.mvvm.BaseViewModel

/**
 * @author Lucas Marciano on 05/04/20.
 */

class MainViewModelImpl(
    interactor: MainContract.Interactor
) : BaseViewModel<MainContract.Interactor>(
    interactor
), MainContract.ViewModel, MainContract.ViewModel.Input, MainContract.ViewModel.Output {

    override val input: MainContract.ViewModel.Input = this
    override val output: MainContract.ViewModel.Output = this
}

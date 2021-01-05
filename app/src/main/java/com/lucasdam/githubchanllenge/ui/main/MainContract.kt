package com.lucasdam.githubchanllenge.ui.main

import com.lucasdam.githubchanllenge.mvvm.BaseContract
import com.lucasdam.githubchanllenge.shared.model.view.Repository

/**
 * @author Lucas Marciano on 05/04/20.
 */

interface MainContract {

    interface ViewModel : BaseContract.ViewModel {
        val input: Input
        val output: Output

        interface Input
        interface Output
    }

    interface Interactor : BaseContract.Interactor

    interface ActivityView : BaseContract.ActivityView

    interface Router : BaseContract.Router {
        fun routeToImageFragment(pokemon: Repository)
    }
}

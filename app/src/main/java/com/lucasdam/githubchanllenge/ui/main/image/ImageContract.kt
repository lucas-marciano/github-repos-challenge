package com.lucasdam.githubchanllenge.ui.main.image

import androidx.lifecycle.MutableLiveData
import com.lucasdam.githubchanllenge.mvvm.BaseContract
import com.lucasdam.githubchanllenge.shared.model.view.Repository

/**
 * @author Lucas Marciano on 03/01/21.
 */

interface ImageContract {

    interface ViewModel: BaseContract.ViewModel {
        val input: Input
        val output: Output

        interface Input {
            fun loadUrl(pokemon: Repository)
        }

        interface Output {
            val onGetRepositoryUrl: MutableLiveData<String>
        }
    }

    interface Interactor: BaseContract.Interactor
}

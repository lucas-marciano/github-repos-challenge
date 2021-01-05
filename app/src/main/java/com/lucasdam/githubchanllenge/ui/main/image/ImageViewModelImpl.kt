package com.lucasdam.githubchanllenge.ui.main.image

import androidx.lifecycle.MutableLiveData
import com.lucasdam.githubchanllenge.mvvm.BaseViewModel
import com.lucasdam.githubchanllenge.shared.model.view.Repository

/**
 * @author Lucas Marciano on 03/01/21.
 */

class ImageViewModelImpl(
    interactor: ImageContract.Interactor
) : BaseViewModel<ImageContract.Interactor>(
    interactor
), ImageContract.ViewModel, ImageContract.ViewModel.Input, ImageContract.ViewModel.Output {

    override val onGetRepositoryUrl: MutableLiveData<String> = MutableLiveData()

    override fun loadUrl(pokemon: Repository) {
        onGetRepositoryUrl.value = pokemon.url
    }

    override val input: ImageContract.ViewModel.Input = this
    override val output: ImageContract.ViewModel.Output = this
}

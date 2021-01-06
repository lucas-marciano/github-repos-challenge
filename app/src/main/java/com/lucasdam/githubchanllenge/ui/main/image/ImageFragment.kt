package com.lucasdam.githubchanllenge.ui.main.image

import com.lucasdam.githubchanllenge.databinding.ImageLayoutBinding
import com.lucasdam.githubchanllenge.mvvm.BaseFragment
import com.lucasdam.githubchanllenge.shared.extensions.observer
import com.lucasdam.githubchanllenge.shared.model.view.Repository
import com.lucasdam.githubchanllenge.ui.main.MainContract
import com.lucasdam.githubchanllenge.ui.main.MainRouterImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Lucas Marciano on 03/01/21.
 */

class ImageFragment :
    BaseFragment<MainContract.ActivityView, ImageContract.ViewModel, ImageLayoutBinding>() {

    override val viewModel: ImageViewModelImpl by viewModel()
    override fun bindView() = initBinding(ImageLayoutBinding.inflate(layoutInflater), this)
    override val module = ImageModule.module

    override fun initialize() {
        viewModel.input.loadUrl(arguments!![MainRouterImpl.KEY] as Repository)
    }

    override fun bindViewModels() {
        viewModel.output.onGetRepositoryUrl.observer(viewLifecycleOwner) {
            bind.tvInfoName.text = it
        }
    }
}

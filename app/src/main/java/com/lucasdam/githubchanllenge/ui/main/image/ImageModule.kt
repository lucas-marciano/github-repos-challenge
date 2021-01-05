package com.lucasdam.githubchanllenge.ui.main.image

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Lucas Marciano on 03/01/21.
 */

object ImageModule {

    val module = module {
        viewModel { ImageViewModelImpl(ImageInteractorImpl()) }
    }
}

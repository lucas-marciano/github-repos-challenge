package com.lucasdam.githubchanllenge.ui.main

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Lucas Marciano on 05/04/20.
 */

object MainModule {

    val module = module {
        viewModel { MainViewModelImpl(MainInteractorImpl()) }
    }
}

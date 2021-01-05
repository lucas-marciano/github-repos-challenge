package com.lucasdam.githubchanllenge.ui.main.home

import androidx.navigation.Navigation
import com.lucasdam.githubchanllenge.R
import com.lucasdam.githubchanllenge.ui.main.MainRouterImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Lucas Marciano on 03/01/21.
 */

object HomeModule {

    val module = module {
        viewModel { (fragment: HomeFragment) ->
            HomeViewModelImpl(
                HomeInteractorImpl(get()),
                get(),
                get(),
                get(),
                MainRouterImpl(
                    fragment.activity!!,
                    Navigation.findNavController(
                        fragment.activity!!,
                        R.id.main_host_fragment
                    )
                )
            )
        }
        factory { HomeAdapter() }
    }
}

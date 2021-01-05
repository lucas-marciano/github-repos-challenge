package com.lucasdam.githubchanllenge.ui.main

import com.lucasdam.githubchanllenge.R
import com.lucasdam.githubchanllenge.mvvm.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Lucas Marciano on 05/04/20.
 */

class MainActivity : BaseActivity<MainContract.ViewModel>(), MainContract.ActivityView {

    override val viewModel: MainViewModelImpl by viewModel()
    override var layoutRes: Int = R.layout.main_layout
    override var module = MainModule.module

    override fun initialize() {
        // not used
    }
}

package com.lucasdam.githubchanllenge.ui.main

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.lucasdam.githubchanllenge.R
import com.lucasdam.githubchanllenge.mvvm.BaseRouter
import com.lucasdam.githubchanllenge.shared.model.view.Repository

/**
 * @author Lucas Marciano on 03/01/21.
 */

class MainRouterImpl(
    activity: Activity,
    navController: NavController
) : BaseRouter(
    activity,
    navController
), MainContract.Router {

    override fun routeToImageFragment(repository: Repository) {
        navController.navigate(
            R.id.home_to_image,
            bundleOf(Pair(KEY, repository)),
            defaultNavOptions
        )
    }

    companion object {
        const val KEY = "key"
    }
}

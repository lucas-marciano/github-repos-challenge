package com.lucasdam.githubchanllenge.mvvm

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.lucasdam.githubchanllenge.R

/**
 * @author Lucas Marciano on 03/01/21.
 */

abstract class BaseRouter(
    protected val activity: Activity,
    protected val navController: NavController
) : BaseContract.Router {

    protected val defaultNavOptions = navOptions {
        anim {
            enter = R.anim.slide_right_in
            exit = R.anim.slide_left_out
            popEnter = R.anim.slide_left_in
            popExit = R.anim.slide_right_out
        }
    }

    override fun onBackPressed() {
        navController.popBackStack().also { isBackStackNotEmpty ->
            if (!isBackStackNotEmpty) finishActivity()
        }
    }

    final override fun finishActivity() {
        activity.finish()
    }
}

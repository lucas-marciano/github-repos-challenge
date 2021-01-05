package com.lucasdam.githubchanllenge.mvvm

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.lucasdam.githubchanllenge.R
import com.lucasdam.githubchanllenge.shared.extensions.setListener
import com.lucasdam.githubchanllenge.shared.extensions.toast
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

/**
 * @author Lucas Marciano on 05/04/20.
 */

abstract class BaseActivity<ViewModelType> : AppCompatActivity(),
    BaseContract.ActivityView where ViewModelType : BaseContract.ViewModel {

    abstract var layoutRes: Int
    abstract var module: Module
    abstract val viewModel: ViewModelType

    private lateinit var loading: View

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutRes)

        loading = LayoutInflater.from(this)
            .inflate(R.layout.fullscreen_loading_layout, null, false)

        addContentView(
            loading,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        loading.visibility = View.GONE

        loadKoinModules(module)
        initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(module)
    }

    protected abstract fun initialize()

    override fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun hideBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun showError(error: String) = toast { error }

    override fun showLoading() {
        loading.apply {
            visibility = View.VISIBLE
            animate()
                .alpha(ANIMATION_ALPHA_VISIBLE)
                .setDuration(ANIMATION_DURATION)
                .setListener(null)
        }
    }

    override fun hideLoading() {
        loading.apply {
            animate()
                .alpha(ANIMATION_ALPHA_GONE)
                .setDuration(ANIMATION_DURATION)
                .setListener(onAnimationEnd = { visibility = View.GONE })
        }
    }

    override fun closeKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun openKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    final override fun showNotImplementedAlert() = toast { "Funcionalidade em desenvolvimento" }

    private companion object {
        const val ANIMATION_ALPHA_GONE = 0F
        const val ANIMATION_ALPHA_VISIBLE = 1F
        const val ANIMATION_DURATION = 200L
    }
}

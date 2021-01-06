package com.lucasdam.githubchanllenge.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

/**
 * @author Lucas Marciano on 05/04/20.
 */
abstract class BaseFragment<ActivityType, ViewModelType, BindingType> : Fragment(),
    BaseContract.ActionView, ViewBindingHolder<BindingType> by ViewBindingHolderImpl()
        where ActivityType : BaseContract.ActivityView,
              ViewModelType : BaseContract.ViewModel,
              BindingType : ViewBinding {

    @Suppress("UNCHECKED_CAST")
    protected val parentActivity: ActivityType by lazy { activity as ActivityType }
    protected val bind: BindingType get() = binding!!

    abstract val module: Module
    abstract val viewModel: ViewModelType

    private var createdView: View? = null
    private var hasInitializedView = false
    var savedInstanceState: Bundle? = null

    protected abstract fun initialize()
    protected abstract fun bindViewModels()
    protected abstract fun bindView(): View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = createdView ?: bindView().also { view -> this.createdView = view }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.savedInstanceState = savedInstanceState

        if (!hasInitializedView) {
            loadKoinModules(module)
            bindViewModels()
            initialize()
            hasInitializedView = true
        }

        viewModel.showLoading.observe(viewLifecycleOwner, Observer { showLoading() })
        viewModel.hideLoading.observe(viewLifecycleOwner, Observer { hideLoading() })
        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(it) })
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(module)
        unbindView()
    }

    override fun showLoading() = parentActivity.showLoading()

    override fun hideLoading() = parentActivity.hideLoading()

    override fun showError(error: String) = parentActivity.showError(error)

    final override fun showNotImplementedAlert() = parentActivity.showNotImplementedAlert()

    fun clearState() {
        savedInstanceState = null
    }
}

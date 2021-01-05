package com.lucasdam.githubchanllenge.mvvm

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding

/**
 * @author Lucas Marciano on 13/04/20.
 */

interface ViewBindingHolder<T : ViewBinding> {
    val binding: T?
    fun initBinding(binding: T, fragment: Fragment): View
    fun unbindView()
}

class ViewBindingHolderImpl<T : ViewBinding> : ViewBindingHolder<T>, LifecycleObserver {
    override var binding: T? = null
    var lifecycle: Lifecycle? = null

    private lateinit var fragmentName: String

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun unbindView() {
        lifecycle?.removeObserver(this)
        lifecycle = null
        binding = null
    }

    override fun initBinding(binding: T, fragment: Fragment): View {
        this.binding = binding
        lifecycle = fragment.viewLifecycleOwner.lifecycle
        lifecycle?.addObserver(this)
        fragmentName = fragment::class.simpleName ?: "N/A"
        return binding.root
    }
}

package com.lucasdam.githubchanllenge.shared.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observer(owner: LifecycleOwner, func: (T) -> Unit) =
    observe(owner, Observer<T> { obs -> obs?.let { value -> func(value) } })
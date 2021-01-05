package com.lucasdam.githubchanllenge.shared.extensions

import androidx.lifecycle.MutableLiveData

/**
 * @author Lucas Marciano on 11/04/2020
 */

fun MutableLiveData<Void>.call() {
    value = null
}

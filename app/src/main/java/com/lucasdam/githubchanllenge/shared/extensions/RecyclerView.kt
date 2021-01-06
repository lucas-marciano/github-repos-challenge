package com.lucasdam.githubchanllenge.shared.extensions

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onScrollToEnd(onScrollNearEnd: (Unit) -> Unit) = addOnScrollListener(object : RecyclerView.OnScrollListener() {
    override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
        if (!rv.canScrollVertically(1)) {
            onScrollNearEnd(Unit)
        }
    }
})
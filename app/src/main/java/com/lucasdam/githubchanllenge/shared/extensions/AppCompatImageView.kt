package com.lucasdam.githubchanllenge.shared.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun AppCompatImageView.loadImage(imageUrl: String, forceCache: Boolean = false) {
    val cacheStrategy = if (forceCache) DiskCacheStrategy.ALL else DiskCacheStrategy.AUTOMATIC
    Glide.with(this.context)
        .load(imageUrl)
        .circleCrop()
        .diskCacheStrategy(cacheStrategy)
        .into(this)
}

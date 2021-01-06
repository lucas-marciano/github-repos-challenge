package com.lucasdam.githubchanllenge.shared.extensions

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Transformations
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lucasdam.githubchanllenge.R

fun AppCompatImageView.loadImage(
    imageUrl: String,
    forceCache: Boolean = false,
    @DrawableRes placeholderRes: Int = R.drawable.ic_placeholder,
    @DrawableRes imageErrorRes: Int = R.drawable.ic_placeholder) {

    val cacheStrategy = if (forceCache) DiskCacheStrategy.ALL else DiskCacheStrategy.AUTOMATIC
    Glide.with(this.context)
        .load(imageUrl)
        .circleCrop()
        .placeholder(placeholderRes)
        .error(imageErrorRes)
        .diskCacheStrategy(cacheStrategy)
        .into(this)
}

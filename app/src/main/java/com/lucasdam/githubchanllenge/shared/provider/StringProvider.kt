package com.lucasdam.githubchanllenge.shared.provider

import android.content.Context
import androidx.annotation.StringRes

/**
 * @author Lucas Marciano on 11/04/2020
 */

interface StringProvider {
    fun getString(@StringRes stringRes: Int): String
}

class StringProviderImpl(private val context: Context) : StringProvider {

    override fun getString(@StringRes stringRes: Int) = context.getString(stringRes)
}

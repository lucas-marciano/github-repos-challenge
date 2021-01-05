package com.lucasdam.githubchanllenge.shared.extensions

import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.annotation.StyleRes

/**
 * @author Lucas Marciano on 05/04/20.
 */

data class SpannableText(
    @StyleRes val style: Int,
    val text: String? = null,
    @StringRes val textRes: Int? = null
)

fun TextView.setSpannableText(vararg list: SpannableText) {

    val spannableString = SpannableString(
        list.map { it.text ?: context.getString(it.textRes!!) }
            .reduce(operation = { text1, text2 -> text1 + text2 })
    )

    var startLength = 0

    for (spannableText in list) {
        val text = spannableText.text ?: context.getString(spannableText.textRes!!)
        spannableString.setSpan(
            TextAppearanceSpan(context, spannableText.style),
            startLength,
            startLength + text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        startLength += text.length
    }

    setText(spannableString, TextView.BufferType.SPANNABLE)
}

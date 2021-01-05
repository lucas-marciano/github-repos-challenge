package com.lucasdam.githubchanllenge.shared.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * @author Lucas Marciano on 05/04/20.
 */

inline fun Context.toast(message: () -> String) =
    Toast.makeText(this, message(), Toast.LENGTH_SHORT).show()

inline fun Fragment.toast(message: () -> String) =
    requireContext().toast(message)

inline fun Context.longToast(message: () -> String) =
    Toast.makeText(this, message(), Toast.LENGTH_LONG).show()

inline fun Fragment.longToast(message: () -> String) =
    requireContext().toast(message)

inline fun Context.color(colorRes: () -> Int) =
    ContextCompat.getColor(this, colorRes())

inline fun Fragment.color(colorRes: () -> Int) =
    requireContext().color(colorRes)

inline fun Context.colorStateList(colorRes: () -> Int) =
    ContextCompat.getColorStateList(this, colorRes())

inline fun Fragment.colorStateList(colorRes: () -> Int) =
    requireContext().colorStateList(colorRes)

inline fun Context.drawable(drawableRes: () -> Int) =
    ContextCompat.getDrawable(this, drawableRes())

inline fun Fragment.drawable(drawableRes: () -> Int) =
    requireContext().drawable(drawableRes)

inline fun Context.startActivity(clazz: () -> Class<*>) =
    startActivity(Intent(this, clazz()))

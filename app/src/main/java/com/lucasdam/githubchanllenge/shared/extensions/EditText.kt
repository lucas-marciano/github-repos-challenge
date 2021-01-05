package com.lucasdam.githubchanllenge.shared.extensions

import android.text.InputType
import android.text.method.DigitsKeyListener
import android.widget.EditText
import com.redmadrobot.inputmask.MaskedTextChangedListener

/**
 * @author Lucas Marciano on 05/04/20.
 */

inline fun EditText.masks(mask: () -> String) {
    val maskedTextChangedListener = MaskedTextChangedListener(mask(), this, null, null)
    inputType = InputType.TYPE_CLASS_NUMBER
    keyListener = DigitsKeyListener.getInstance("0123456789 -.,()/")
    addTextChangedListener(maskedTextChangedListener)
}

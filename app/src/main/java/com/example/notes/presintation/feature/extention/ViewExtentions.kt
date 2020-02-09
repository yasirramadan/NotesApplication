package com.example.notes.presintation.feature.extention

import android.content.Context
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.enable() {
    isFocusable = true
    isFocusableInTouchMode = true

    inputType = InputType.TYPE_CLASS_TEXT

    requestFocus()

    text?.let {
        setSelection(it.toString().length)
    }

    showKeyboard()
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
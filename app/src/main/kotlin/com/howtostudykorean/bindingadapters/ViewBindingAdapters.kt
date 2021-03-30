package com.howtostudykorean.bindingadapters

import android.content.Context
import androidx.databinding.BindingAdapter
import android.view.View
import android.view.inputmethod.InputMethodManager

val View.inputMethodManager: InputMethodManager
    get() = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun View.showKeyboard() {
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0)
}

@BindingAdapter("onLongClick")
fun onLongClick(view: View, runnable: Runnable?) {
    view.setOnLongClickListener {
        runnable?.run()
        true
    }
}
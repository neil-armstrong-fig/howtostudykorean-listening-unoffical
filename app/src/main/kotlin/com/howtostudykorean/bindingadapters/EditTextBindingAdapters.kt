package com.howtostudykorean.bindingadapters

import androidx.databinding.BindingAdapter
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.EditText

@BindingAdapter("onKeyboardDone")
fun onKeyboardDone(editText: EditText, runnable: Runnable?) {
    editText.imeOptions = IME_ACTION_DONE
    editText.setOnEditorActionListener { _, actionId: Int, _ ->
        if (actionId == IME_ACTION_DONE) {
            runnable?.run()
            true
        } else {
            false
        }
    }
}

@BindingAdapter("onEnterKeyPressed")
fun onEnterKeyPressed(editText: EditText, runnable: Runnable?) {
    editText.setOnKeyListener { _, keyCode, _ ->
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            runnable?.run()
            true
        } else {
            false
        }
    }
}

@BindingAdapter("hasFocus")
fun setFocus(editText: EditText, hasFocus: Boolean) {
    if (hasFocus) {
        editText.requestFocus()
        editText.showKeyboard()
    } else {
        editText.clearFocus()
    }
}
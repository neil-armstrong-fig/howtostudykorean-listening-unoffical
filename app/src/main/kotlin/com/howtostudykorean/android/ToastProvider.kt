package com.howtostudykorean.android

import android.content.Context
import androidx.annotation.StringRes
import android.widget.Toast
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ToastProvider @Inject constructor(
    private val context: Context,
    private val resourceService: ResourceService
) {
    open fun showToast(@StringRes text: Int, length: Int = Toast.LENGTH_SHORT) {
        if (length > 1) throw RuntimeException("Length cannot be higher than 1")
        Toast.makeText(context, resourceService.getStringResource(text), length).show()
    }
}
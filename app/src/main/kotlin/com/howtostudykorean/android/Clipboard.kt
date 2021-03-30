package com.howtostudykorean.android

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class Clipboard @Inject constructor(context: Context) {
    private val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    open fun copyToClipboard(text: String) {
        clipboard.setPrimaryClip(ClipData.newPlainText("korean-text", text))
    }
}
package com.howtostudykorean.bindingadapters

import androidx.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.TextView

@BindingAdapter("selected", "skipped", "selectedBackground", "defaultBackground", "skippedBackground")
fun setAudioTrackBackground(
    textView: TextView,
    selected: Boolean,
    skipped: Boolean,
    selectedBackground: Drawable,
    defaultBackground: Drawable,
    skippedBackground: Drawable
) {
    when {
        selected -> textView.background = selectedBackground
        skipped -> textView.background = skippedBackground
        else -> textView.background = defaultBackground
    }
}
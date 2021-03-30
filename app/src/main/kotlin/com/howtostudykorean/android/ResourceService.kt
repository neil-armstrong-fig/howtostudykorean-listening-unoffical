package com.howtostudykorean.android

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ResourceService @Inject constructor(private val applicationContext: Context) {
    open fun getStringResource(@StringRes id: Int) = applicationContext.resources.getString(id)
    open fun getColorResource(@ColorRes id: Int) = ContextCompat.getColor(applicationContext, id)
    open fun getDrawableResource(@DrawableRes id: Int) = ContextCompat.getDrawable(applicationContext, id)
}

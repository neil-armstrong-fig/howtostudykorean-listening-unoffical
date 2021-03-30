package com.howtostudykorean.android

import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class ActivityViewModel {
    @Inject lateinit var resourceService: ResourceService

    open fun onCreate(activity: AppCompatActivity) = Unit
    open fun onDestroy() = Unit
}

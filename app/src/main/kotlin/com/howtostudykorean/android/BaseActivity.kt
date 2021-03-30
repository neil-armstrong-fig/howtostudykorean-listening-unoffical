package com.howtostudykorean.android

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

typealias NoViewModel = Nothing

abstract class BaseActivity<ViewModelType : ActivityViewModel> : AppCompatActivity() {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    var viewModel: ViewModelType? = null
        private set

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.onDestroy()
    }

    fun attachViewModel(viewModel: ViewModelType) {
        this.viewModel = viewModel
        viewModel.onCreate(this)
    }
}

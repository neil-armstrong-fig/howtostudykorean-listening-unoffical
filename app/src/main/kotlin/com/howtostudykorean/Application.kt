package com.howtostudykorean

import androidx.multidex.MultiDexApplication
import com.howtostudykorean.dagger.AppModule
import com.howtostudykorean.dagger.ApplicationComponent
import com.howtostudykorean.dagger.DaggerApplicationComponent
import com.howtostudykorean.downloading.AudioTrackContainer
import javax.inject.Inject

open class Application : MultiDexApplication() {
    @Inject lateinit var audioTrackContainer: AudioTrackContainer // Required for initialization

    companion object {
        lateinit var dependencies: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        dependencies = DaggerApplicationComponent
                .builder()
                .appModule(AppModule(this))
                .build()
        dependencies.inject(this)
    }
}

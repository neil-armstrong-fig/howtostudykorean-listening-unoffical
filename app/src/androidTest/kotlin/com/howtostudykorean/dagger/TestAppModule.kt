package com.howtostudykorean.dagger

import android.content.Context
import com.howtostudykorean.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule(private val application: Application) {
    @Singleton
    @Provides
    fun provideApplication(): android.app.Application = application

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = application
}
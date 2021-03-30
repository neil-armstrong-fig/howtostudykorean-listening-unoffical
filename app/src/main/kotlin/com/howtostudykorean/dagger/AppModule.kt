package com.howtostudykorean.dagger

import android.content.Context
import com.howtostudykorean.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplication(): android.app.Application = application

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application
}
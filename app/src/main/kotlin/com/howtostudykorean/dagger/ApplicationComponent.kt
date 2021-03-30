package com.howtostudykorean.dagger

import com.howtostudykorean.Application
import com.howtostudykorean.views.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {
    fun inject(application: Application)

    // Activities
    fun inject(mainActivity: MainActivity)
}

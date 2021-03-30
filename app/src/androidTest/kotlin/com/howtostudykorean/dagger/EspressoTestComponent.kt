package com.howtostudykorean.dagger

import com.howtostudykorean.downloading.AudioDownloaderTest
import com.howtostudykorean.downloading.WebCrawlerTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class])
interface EspressoTestComponent : ApplicationComponent {
    fun inject(audioDownloaderTest: AudioDownloaderTest)
    fun inject(webCrawlerTest: WebCrawlerTest)
}
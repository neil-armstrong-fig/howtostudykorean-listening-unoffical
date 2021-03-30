package com.howtostudykorean.dagger

import androidx.test.platform.app.InstrumentationRegistry
import com.howtostudykorean.Application
import com.howtostudykorean.downloading.AudioDownloaderTest
import com.howtostudykorean.downloading.WebCrawlerTest
import org.junit.rules.ExternalResource

class DaggerRule(private val testClass: Any) : ExternalResource() {
    override fun before() {
        val application = InstrumentationRegistry
                .getInstrumentation()
                .targetContext
                .applicationContext as Application

        val component = DaggerEspressoTestComponent
                .builder()
                .testAppModule(TestAppModule(application))
                .build()

        Application.dependencies = component

        inject(testClass, component)
    }

    private fun inject(testClass: Any, component: EspressoTestComponent) {
        when (testClass) {
            is AudioDownloaderTest -> component.inject(testClass)
            is WebCrawlerTest -> component.inject(testClass)
        }
    }
}

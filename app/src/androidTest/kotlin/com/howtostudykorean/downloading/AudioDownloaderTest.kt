package com.howtostudykorean.downloading

import androidx.test.platform.app.InstrumentationRegistry
import com.howtostudykorean.dagger.DaggerRule
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File
import javax.inject.Inject

class AudioDownloaderTest {
    @Rule @JvmField val daggerRule = DaggerRule(this)

    private val testFileName = "한국"
    private val forwardSlashFileName = "나는 여자야 / 저는 여자예요"
    private val internalFileDirectory = File(InstrumentationRegistry.getContext().cacheDir.absolutePath.replace(".test", ""))

    @Inject lateinit var target: AudioDownloader

    @Before
    @After
    fun deleteTestFile() {
        testFile().delete()
        forwardSlashFile().delete()
    }

    @Test
    fun shouldDownloadMp3File() {
        target.downloadAudio("https://www.howtostudykorean.com/wp-content/uploads/2014/01/wLesson1-1.mp3", testFileName).blockingAwait()

        assertThat(testFile().exists(), `is`(true))
    }

    @Test
    fun shouldSaveFileWithForwardSlash() {
        target.downloadAudio("https://www.howtostudykorean.com/wp-content/uploads/2014/01/Lesson-1-2.mp3", forwardSlashFileName).blockingAwait()

        assertThat(forwardSlashFile().exists(), `is`(true))
    }

    @Test
    fun shouldProvideSimpleCacheIfTheAudioAlreadyExists() {
        target.downloadAudio("https://www.howtostudykorean.com/wp-content/uploads/2014/01/wLesson1-1.mp3", testFileName).blockingAwait()
        target.downloadAudio("https://www.howtostudykorean.com/wp-content/uploads/2014/01/wLesson1-1.mp3", testFileName).blockingAwait()
    }

    private fun testFile() = File(internalFileDirectory, "$testFileName.mp3")
    private fun forwardSlashFile() = File(internalFileDirectory, "${forwardSlashFileName.replace("/", "or")}.mp3")
}
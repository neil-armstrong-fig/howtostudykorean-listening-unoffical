package com.howtostudykorean.downloading

import com.howtostudykorean.android.NonNullObservableField
import com.howtostudykorean.extensions.logger
import com.howtostudykorean.persistance.Settings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.rx2.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AudioTrackContainer @Inject constructor(
    private val webCrawler: WebCrawler,
    private val audioDownloader: AudioDownloader,
    settings: Settings
) {
    private val logger = logger()

    open val audioTracks = NonNullObservableField<List<AudioTrack>>(emptyList())

    init {
        val lessonNumber = settings.loadCurrentLesson()
        downloadAudioTracks(getPageForLessonNumber(lessonNumber))
    }

    fun loadPage(lessonNumber: Int) {
        downloadAudioTracks(getPageForLessonNumber(lessonNumber))
    }

    private fun downloadAudioTracks(page: Page) {
        try {
            audioTracks.set(emptyList())
            GlobalScope.async {
                audioTracks.set(webCrawler.extractAudioFromUrl(page).await())
                audioTracks.get().forEach {
                    audioDownloader.downloadAudio(it.audioTrackUrl, it.audioText).await()
                }
            }
        } catch (e: Exception) {
            logger.error("Failed to download audio tracks", e)
        }
    }
}
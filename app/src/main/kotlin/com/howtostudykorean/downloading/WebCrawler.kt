package com.howtostudykorean.downloading

import kotlinx.coroutines.rx2.rxSingle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class WebCrawler @Inject constructor(
    private val lessonTextConverter: LessonTextConverter,
    private val pageDownloader: PageDownloader
) {
    open fun extractAudioFromUrl(page: Page) = rxSingle {
        val document = pageDownloader.downloadPage(page)
        return@rxSingle document.select("a")
            .asSequence()
            .filter { it.hasAttr("href") }
            .filter { it.attr("href").contains(".mp3") }
            .distinctBy { it.text() }
            .map { lessonTextConverter.toAudioTrack(it, page) }
            .filter { it != null }
            .requireNoNulls()
            .toList()
    }
}

package com.howtostudykorean.downloading

import android.content.Context
import com.howtostudykorean.R
import com.howtostudykorean.android.ToastProvider
import com.howtostudykorean.downloading.exceptions.PageDownloadException
import com.howtostudykorean.extensions.logger
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PageDownloader @Inject constructor(
    private val androidContext: Context,
    private val toastProvider: ToastProvider
) {
    private val logger = logger()

    fun downloadPage(page: Page): Document {
        try {
            val documentFromWeb = loadHtmlFromWeb(page)
            saveHtmlToFile(page, documentFromWeb)
            return documentFromWeb
        } catch (e: Exception) {
            logger.error("Failed to download page, trying from file", e)
        }

        try {
            return loadHtmlFromFile(page)
        } catch (e: Exception) {
            logger.error("Failed to load html from file", e)
            toastProvider.showToast(R.string.page_download_fail)
            throw PageDownloadException()
        }
    }

    private fun loadHtmlFromWeb(page: Page) = Jsoup.connect(page.url).get()

    private fun saveHtmlToFile(page: Page, document: Document) {
        page.toFile().writeText(document.html())
    }

    private fun loadHtmlFromFile(page: Page): Document {
        val rawHtml = page.toFile().readText()
        return Jsoup.parse(rawHtml)
    }

    private fun Page.toFile() = File(androidContext.cacheDir.path, "$name.html")
}
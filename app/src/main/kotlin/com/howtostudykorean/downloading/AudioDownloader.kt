package com.howtostudykorean.downloading

import android.content.Context
import com.howtostudykorean.extensions.logger
import com.howtostudykorean.extensions.replaceForwardSlash
import kotlinx.coroutines.rx2.rxCompletable
import java.io.File
import java.io.InputStream
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AudioDownloader @Inject constructor(private val androidContext: Context) {
    private val logger = logger()

    open fun downloadAudio(url: String, fileName: String) = rxCompletable {
        val filteredFilename = fileName.replaceForwardSlash()
        if (File(androidContext.cacheDir.path, "$filteredFilename.mp3").exists()) return@rxCompletable

        openConnection(url) { connection ->
            connection.use { webResource ->
                try {
                    File(androidContext.cacheDir.path, "$filteredFilename.mp3").outputStream().use { fileOnDevice ->
                        webResource.copyTo(fileOnDevice)
                    }
                } catch (e: Exception) {
                    logger.error("Failed to download audio file, will continue and hope it is cached", e)
                }
            }
        }
    }

    private fun openConnection(url: String, onConnected: (InputStream) -> Unit) {
        try {
            val connection = URL(url).openConnection().inputStream
            onConnected(connection)
        } catch (e: Exception) {
            logger.error("Failed to open http connection, poor internet?")
        }
    }
}
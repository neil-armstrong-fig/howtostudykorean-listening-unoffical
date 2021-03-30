package com.howtostudykorean.android

import android.content.Context
import android.media.MediaPlayer
import com.howtostudykorean.extensions.logger
import com.howtostudykorean.extensions.replaceForwardSlash
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class SoundPlayer @Inject constructor(private val context: Context) {
    private val logger = logger()

    private var currentMedia: MediaPlayer? = null

    open fun playSound(fileName: String) {
        try {
            stopCurrentAudio()
            currentMedia = MediaPlayer().apply {
                setOnErrorListener { _, what, extra ->
                    logger.error("Couldn't play sound with error code: '{}', extra code: '{}'", what, extra)
                    true
                }
                setOnCompletionListener { mediaPlayer -> mediaPlayer?.release() }
                setDataSource("${context.cacheDir.absolutePath}/${fileName.replaceForwardSlash()}.mp3")
                prepare()
                start()
            }
        } catch (exception: Throwable) {
            logger.error("Couldn't play sound", exception)
        }
    }

    private fun stopCurrentAudio() {
        val currentMedia = currentMedia ?: return
        try {
            currentMedia.stop()
        } catch (exception: Throwable) {
            logger.warn("Couldn't stop media player, as it was probably already stopped", exception)
        } finally {
            currentMedia.release()
        }
    }
}

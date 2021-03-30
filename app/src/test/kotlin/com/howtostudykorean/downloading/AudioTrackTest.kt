package com.howtostudykorean.downloading

import com.howtostudykorean.builders.audioTrack
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class AudioTrackTest {
    @Test
    fun shouldResetAllAudioTracks() {
        val audioTracks = listOf(audioTrackWithMetaDataSetToTrue(), audioTrackWithMetaDataSetToTrue())

        audioTracks.reset()

        assertThatMetaDataIsFalse(audioTracks[0])
        assertThatMetaDataIsFalse(audioTracks[1])
    }

    private fun assertThatMetaDataIsFalse(audioTrack: AudioTrack) {
        assertThat(audioTrack.revealed.get(), `is`(false))
        assertThat(audioTrack.selected.get(), `is`(false))
        assertThat(audioTrack.skipped.get(), `is`(false))
    }

    private fun audioTrackWithMetaDataSetToTrue() = audioTrack(revealed = true, selected = true, skipped = true)
}
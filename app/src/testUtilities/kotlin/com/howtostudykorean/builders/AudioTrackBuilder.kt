package com.howtostudykorean.builders

import com.howtostudykorean.downloading.AudioTrack

fun audioTrack(
    audioText: String = "한국",
    audioTrackUrl: String = "https://www.howtostudykorean.com/wp-content/uploads/2014/01/wLesson1-1.mp3",
    revealed: Boolean = false,
    selected: Boolean = false,
    skipped: Boolean = false
): AudioTrack {
    return AudioTrack(audioText, audioTrackUrl).apply {
        this.revealed.set(revealed)
        this.selected.set(selected)
        this.skipped.set(skipped)
    }
}
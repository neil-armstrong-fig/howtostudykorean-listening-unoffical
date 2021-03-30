package com.howtostudykorean.downloading

import com.howtostudykorean.android.NonNullObservableField

class AudioTrack(val audioText: String, val audioTrackUrl: String) {
    val revealed = NonNullObservableField(false)
    val skipped = NonNullObservableField(false)
    val selected = NonNullObservableField(false)
    val unrevealedText = "-"

    fun reset() {
        selected.set(false)
        revealed.set(false)
        skipped.set(false)
    }
}

fun List<AudioTrack>.reset() = forEach {
    it.reset()
}
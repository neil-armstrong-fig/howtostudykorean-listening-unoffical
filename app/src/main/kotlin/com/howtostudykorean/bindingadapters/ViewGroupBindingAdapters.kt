package com.howtostudykorean.bindingadapters

import android.content.Context
import androidx.databinding.BindingAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.howtostudykorean.databinding.AudioTrackRowBinding
import com.howtostudykorean.downloading.AudioTrack
import com.howtostudykorean.views.main.MainViewModel

@BindingAdapter("audioTracks", "vm")
fun addAudioTracks(viewGroup: ViewGroup, audioTracks: List<AudioTrack>, vm: MainViewModel) {
    viewGroup.removeAllViews()
    val inflater = viewGroup.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    audioTracks.forEachIndexed { index, audioTrack ->
        AudioTrackRowBinding.inflate(inflater, viewGroup, true).apply {
            this.audioTrack = audioTrack
            this.index = index.toString()
            this.vm = vm
        }
    }
}

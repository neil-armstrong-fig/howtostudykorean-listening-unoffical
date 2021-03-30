package com.howtostudykorean.views.main

import android.widget.Toast
import androidx.databinding.ObservableField
import com.howtostudykorean.R
import com.howtostudykorean.android.ActivityViewModel
import com.howtostudykorean.android.Clipboard
import com.howtostudykorean.android.NonNullObservableField
import com.howtostudykorean.android.SoundPlayer
import com.howtostudykorean.android.ToastProvider
import com.howtostudykorean.coroutine.CoroutineContexts
import com.howtostudykorean.downloading.AudioTrack
import com.howtostudykorean.downloading.AudioTrackContainer
import com.howtostudykorean.extensions.addOnPropertyChangedCallback
import com.howtostudykorean.persistance.Settings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class MainViewModel @Inject constructor(
    private val toastProvider: ToastProvider,
    private val soundPlayer: SoundPlayer,
    private val clipboard: Clipboard,
    private val settings: Settings,
    private val coroutineContexts: CoroutineContexts,
    audioTrackContainer: AudioTrackContainer
) : ActivityViewModel() {
    val audioTracks = audioTrackContainer.audioTracks
    val guess = NonNullObservableField("")
    val selectedAudioTrack = ObservableField<AudioTrack>()
    val started = NonNullObservableField(false)

    var currentAudioTrackIndex = 0

    init {
        audioTracks.addOnPropertyChangedCallback {
            if (!audioTracks.get().isEmpty()) {
                var currentIndex = settings.loadCurrentAudioTrackIndex()
                if (currentIndex >= audioTracks.get().size) currentIndex = audioTracks.get().size - 1
                setAudioTrackIndex(currentIndex)
                updateSelectedAudioTrack()
                setFailedAudioTracks()
                selectedAudioTrack.get()?.selected?.set(false)
            } else {
                started.set(false)
                clearGuess()
            }
        }

        guess.addOnPropertyChangedCallback {
            if (guess.get() == selectedAudioTrack.get()?.audioText) {
                handleCorrectGuess()
            }
        }
    }

    private fun setFailedAudioTracks() {
        settings.loadFailedAudioTrackIndexes().forEach {
            audioTracks.get()[it].skipped.set(true)
        }
    }

    fun playSound(audioTrack: AudioTrack) {
        if (started.get() && !audioTrack.selected.get() && !audioTrack.revealed.get()) return
        soundPlayer.playSound(audioTrack.audioText)
    }

    fun longPressed(audioTrack: AudioTrack) {
        clipboard.copyToClipboard(audioTrack.audioText)
    }

    fun submitGuess() {
        if (guess.get() == selectedAudioTrack.get()?.audioText) {
            handleCorrectGuess()
        } else {
            handleIncorrectGuess()
        }
    }

    private fun handleCorrectGuess() {
        setAudioTrackIndex(currentAudioTrackIndex + 1)
        if (currentAudioTrackIndex < audioTracks.get().size) {
            toastProvider.showToast(R.string.successful_guess)
            selectedAudioTrack.get()?.revealed?.set(true)
            updateSelectedAudioTrack()
            playSelectedAudio()
            clearGuess()
        } else {
            handleFinished()
        }
    }

    private fun handleFinished() {
        toastProvider.showToast(R.string.finished_guessing, Toast.LENGTH_LONG)
        setAudioTrackIndex(currentAudioTrackIndex + 1)
        started.set(false)
    }

    private fun updateSelectedAudioTrack() {
        if (currentAudioTrackIndex > audioTracks.get().size) {
            selectedAudioTrack.set(audioTracks.get().last())
            return
        }

        val newAudioTrack = audioTracks.get()[currentAudioTrackIndex]
        selectedAudioTrack.get()?.selected?.set(false)
        newAudioTrack.selected.set(true)
        selectedAudioTrack.set(newAudioTrack)
    }

    private fun clearGuess() {
        GlobalScope.launch(coroutineContexts.Main) {
            guess.set("")
        }
    }

    private fun handleIncorrectGuess() {
        toastProvider.showToast(R.string.incorrect_guess)
    }

    fun startQuiz() {
        if (currentAudioTrackIndex > audioTracks.get().size) currentAudioTrackIndex = audioTracks.get().size - 1
        started.set(true)
        revealAudioTracksUpToIndex()
        playSelectedAudio()
        selectedAudioTrack.get()?.selected?.set(true)
    }

    private fun revealAudioTracksUpToIndex() {
        repeat(currentAudioTrackIndex) { index ->
            audioTracks.get()[index].selected.set(false)
            audioTracks.get()[index].revealed.set(true)
        }
    }

    fun playSelectedAudio() {
        soundPlayer.playSound(selectedAudioTrack.get()!!.audioText)
    }

    fun skip() {
        setAudioTrackIndex(currentAudioTrackIndex + 1)
        if (audioTracks.get().size != currentAudioTrackIndex) {
            toastProvider.showToast(R.string.skip_guess)
            selectedAudioTrack.get()?.skipped?.set(true)
            selectedAudioTrack.get()?.revealed?.set(true)
            updateSettingsWithSkippedTracks()
            updateSelectedAudioTrack()
            playSelectedAudio()
        } else {
            handleFinished()
        }
    }

    private fun updateSettingsWithSkippedTracks() {
        val indexesOfSkippedTracks = mutableListOf<Int>()
        audioTracks.get().forEachIndexed { index, audioTrack ->
            if (audioTrack.skipped.get()) indexesOfSkippedTracks.add(index)
        }
        settings.saveFailedAudioTrackIndexes(indexesOfSkippedTracks)
    }

    fun restartQuiz() {
        setAudioTrackIndex(0)
        audioTracks.get().forEach { it.reset() }
        updateSelectedAudioTrack()
        playSelectedAudio()
        resetSettings()
    }

    private fun resetSettings() {
        settings.clearFailedAudioTrackIndexs()
    }

    private fun setAudioTrackIndex(newIndex: Int) {
        currentAudioTrackIndex = newIndex
        settings.saveCurrentAudioTrackIndex(currentAudioTrackIndex)
    }
}
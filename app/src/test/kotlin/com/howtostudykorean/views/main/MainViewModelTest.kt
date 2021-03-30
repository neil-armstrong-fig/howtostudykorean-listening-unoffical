package com.howtostudykorean.views.main

import com.howtostudykorean.R
import com.howtostudykorean.android.Clipboard
import com.howtostudykorean.android.NonNullObservableField
import com.howtostudykorean.android.SoundPlayer
import com.howtostudykorean.android.ToastProvider
import com.howtostudykorean.builders.audioTrack
import com.howtostudykorean.coroutine.TestCoroutineContexts
import com.howtostudykorean.downloading.AudioTrackContainer
import com.howtostudykorean.persistance.Settings
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private val soundPlayer = mock<SoundPlayer>()
    private val toastProvider = mock<ToastProvider>()
    private val clipBoard = mock<Clipboard>()
    private val settings = mock<Settings>()
    private val audioTrackContainer = mock<AudioTrackContainer>()

    private val audioTrack = audioTrack()
    private val audioTrack2 = audioTrack(audioText = "안녕")
    private val audioTracks = listOf(audioTrack, audioTrack2)

    private lateinit var target: MainViewModel

    @Before
    fun beforeEachTest() = runBlocking {
        val audioTracksObservableField = NonNullObservableField(audioTracks)
        given(audioTrackContainer.audioTracks).willReturn(audioTracksObservableField)
        given(settings.loadFailedAudioTrackIndexes()).willReturn(emptyList())
        target = MainViewModel(toastProvider, soundPlayer, clipBoard, settings, TestCoroutineContexts(), audioTrackContainer)
        audioTracksObservableField.notifyChange()
    }

    @Test
    fun shouldDownloadAudioTracks() {
        assertThat(target.audioTracks.get(), `is`(audioTracks))
        assertThat(audioTrack.selected.get(), `is`(false))
        verify(soundPlayer, never()).playSound(any())
    }

    @Test
    fun shouldPlaySoundWhenRevealed() {
        audioTrack.revealed.set(true)

        target.playSound(audioTrack)

        verify(soundPlayer).playSound(audioTrack.audioText)
    }

    @Test
    fun shouldNotPlayAudioTrackIfItIsNotSelectedAndRevealed() {
        target.started.set(true)
        audioTrack.revealed.set(false)
        audioTrack.selected.set(false)

        target.playSound(audioTrack)

        verify(soundPlayer, never()).playSound(any())
    }

    @Test
    fun shouldPlayAudioTrackIfNotStarted() {
        target.started.set(false)
        audioTrack.revealed.set(false)
        audioTrack.selected.set(false)

        target.playSound(audioTrack)

        verify(soundPlayer).playSound(audioTrack.audioText)
    }

    @Test
    fun shouldRevealSelectedAudioTrackAndSelectNextIfGuessIsCorrect() {
        target.guess.set(audioTrack.audioText)

        assertThat(target.selectedAudioTrack.get(), `is`(audioTrack2))
        assertThat(audioTrack.revealed.get(), `is`(true))
        assertThat(audioTrack.selected.get(), `is`(false))
        assertThat(audioTrack2.revealed.get(), `is`(false))
        assertThat(audioTrack2.selected.get(), `is`(true))
        assertThat(target.guess.get(), `is`(""))
        verify(toastProvider).showToast(R.string.successful_guess)
        verify(soundPlayer).playSound(audioTrack2.audioText)
        verify(settings).saveCurrentAudioTrackIndex(1)
    }

    @Test
    fun shouldDisplaySuccessToastWhenFinished() {
        target.guess.set(audioTrack.audioText)
        target.guess.set(audioTrack2.audioText)

        assertThat(target.started.get(), `is`(false))
        verify(toastProvider).showToast(R.string.successful_guess)
        verify(soundPlayer).playSound(audioTrack2.audioText)
    }

    @Test
    fun shouldNotChangeSelectedTrackIfGuessIsFalse() {
        target.guess.set("incorrect")

        target.startQuiz()
        target.submitGuess()

        assertThat(target.selectedAudioTrack.get(), `is`(audioTrack))
        assertThat(audioTrack.revealed.get(), `is`(false))
        assertThat(audioTrack.selected.get(), `is`(true))
        assertThat(audioTrack2.revealed.get(), `is`(false))
        assertThat(audioTrack2.selected.get(), `is`(false))
        verify(toastProvider).showToast(R.string.incorrect_guess)
        verify(soundPlayer).playSound(audioTrack.audioText)
        verify(settings, never()).saveCurrentAudioTrackIndex(1)
    }

    @Test
    fun shouldHaveStartSetToTrueAtFirstLaunch() {
        assertThat(target.started.get(), `is`(false))
    }

    @Test
    fun shouldSetStartedToTrueWhenStartQuizCalled() {
        target.startQuiz()

        assertThat(target.started.get(), `is`(true))
        assertThat(audioTrack.selected.get(), `is`(true))
        verify(soundPlayer).playSound(audioTrack.audioText)
    }

    @Test
    fun shouldRevealAnswersIfIndexIsGreaterThanZero() {
        target.selectedAudioTrack.set(audioTrack2)
        target.currentAudioTrackIndex = 1

        target.startQuiz()

        assertThat(target.started.get(), `is`(true))
        assertThat(audioTrack.selected.get(), `is`(false))
        assertThat(audioTrack.revealed.get(), `is`(true))
        assertThat(audioTrack2.selected.get(), `is`(true))
        assertThat(audioTrack2.revealed.get(), `is`(false))
        verify(soundPlayer).playSound(audioTrack2.audioText)
    }

    @Test
    fun shouldPlaySelectedAudio() {
        target.playSelectedAudio()

        verify(soundPlayer).playSound(audioTrack.audioText)
    }

    @Test
    fun shouldSkipAudio() {
        target.guess.set("A Guess")

        target.skip()

        assertThat(target.guess.get(), `is`("A Guess"))
        verify(toastProvider).showToast(R.string.skip_guess)
        assertThat(audioTrack.skipped.get(), `is`(true))
        assertThat(audioTrack.revealed.get(), `is`(true))
        assertThat(target.selectedAudioTrack.get(), `is`(audioTrack2))
        verify(settings).saveFailedAudioTrackIndexes(listOf(0))
    }

    @Test
    fun shouldRestartQuiz() {
        target.guess.set(audioTrack.audioText)
        target.submitGuess()

        target.restartQuiz()

        assertThat(target.selectedAudioTrack.get(), `is`(audioTrack))
        assertThat(audioTrack.revealed.get(), `is`(false))
        assertThat(audioTrack.selected.get(), `is`(true))
        assertThat(audioTrack2.revealed.get(), `is`(false))
        assertThat(audioTrack2.selected.get(), `is`(false))
        assertThat(audioTrack2.skipped.get(), `is`(false))
        verify(settings, times(2)).saveCurrentAudioTrackIndex(0)
        verify(settings).clearFailedAudioTrackIndexs()
    }

    @Test
    fun shouldCopyTextToClipboardOnLongPress() {
        target.longPressed(audioTrack)

        verify(clipBoard).copyToClipboard(audioTrack.audioText)
    }

    @Test
    fun shouldSetFailedAudioTracksOnLoad() {
        given(settings.loadFailedAudioTrackIndexes()).willReturn(listOf(0))
        target.audioTracks.set(audioTracks)
        target.audioTracks.notifyChange()

        assertThat(audioTrack.skipped.get(), `is`(true))
    }
}
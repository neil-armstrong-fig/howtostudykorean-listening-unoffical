package com.howtostudykorean.persistance

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

private const val SHARED_PREFS_FILE_NAME = "lesson_prefs"

private const val CURRENT_LESSON = "current_lesson"
private const val AUDIO_TRACK_INDEX = "current_audio_index"
private const val FAILED_AUDIO_TRACK_INDEXES = "failed_audio_track_indexes"

@Singleton
open class Settings @Inject constructor(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE)

    open fun loadCurrentLesson() = sharedPreferences.getInt(CURRENT_LESSON, 1)
    open fun saveCurrentLesson(lessonNumber: Int) = saveToSharedPreferences(CURRENT_LESSON, lessonNumber)

    open fun loadCurrentAudioTrackIndex() = sharedPreferences.getInt(AUDIO_TRACK_INDEX, 0)
    open fun saveCurrentAudioTrackIndex(index: Int) = saveToSharedPreferences(AUDIO_TRACK_INDEX, index)

    open fun loadFailedAudioTrackIndexes(): List<Int> {
        val failedAudioTracks = sharedPreferences.getString(FAILED_AUDIO_TRACK_INDEXES, "")
        if (failedAudioTracks == null || failedAudioTracks == "") return emptyList()

        return failedAudioTracks
                .split(",")
                .map(String::toInt)
    }
    open fun clearFailedAudioTrackIndexs() = saveToSharedPreferences(FAILED_AUDIO_TRACK_INDEXES, "")
    open fun saveFailedAudioTrackIndexes(indexes: List<Int>) {
        if (indexes.isEmpty()) return clearFailedAudioTrackIndexs()

        val stringBuilder = StringBuilder()
        indexes.forEach {
            stringBuilder
                    .append(it)
                    .append(",")
        }
        val listWithoutLastComma = stringBuilder.substring(0, stringBuilder.length - 1)
        saveToSharedPreferences(FAILED_AUDIO_TRACK_INDEXES, listWithoutLastComma)
    }

    private fun saveToSharedPreferences(key: String, value: Int) = with(sharedPreferences.edit()) {
        putInt(key, value)
        apply()
    }

    private fun saveToSharedPreferences(key: String, value: String) = with(sharedPreferences.edit()) {
        putString(key, value)
        apply()
    }
}
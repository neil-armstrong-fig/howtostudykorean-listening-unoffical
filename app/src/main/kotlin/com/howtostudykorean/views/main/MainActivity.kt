package com.howtostudykorean.views.main

import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.howtostudykorean.Application
import com.howtostudykorean.R
import com.howtostudykorean.android.BaseActivity
import com.howtostudykorean.bindingadapters.hideKeyboard
import com.howtostudykorean.databinding.ActivityMainBinding
import com.howtostudykorean.downloading.AudioTrackContainer
import com.howtostudykorean.persistance.Settings
import com.howtostudykorean.views.about.AboutActivity
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_main.nav_burger
import kotlinx.android.synthetic.main.activity_main.nav_view
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {
    @Inject lateinit var mainViewModel: MainViewModel
    @Inject lateinit var audioTrackContainer: AudioTrackContainer
    @Inject lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.dependencies.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = mainViewModel

        val currentLessonNumber = settings.loadCurrentLesson()
        nav_view.menu.getItem(currentLessonNumber - 1).isChecked = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) nav_view.menu.setGroupDividerEnabled(true)

        nav_view.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.about) {
                startActivity(Intent(this, AboutActivity::class.java))
                return@setNavigationItemSelectedListener true
            }

            menuItem.isChecked = true
            drawer_layout.closeDrawers()

            settings.saveCurrentAudioTrackIndex(0)
            settings.clearFailedAudioTrackIndexs()

            val lessonNumber = extractLessonNumber(menuItem.title.toString())
            audioTrackContainer.loadPage(lessonNumber)
            settings.saveCurrentLesson(lessonNumber)

            true
        }

        nav_burger.setOnClickListener {
            if (!drawer_layout.isDrawerOpen(GravityCompat.START)) {
                binding.root.hideKeyboard()
                drawer_layout.openDrawer(GravityCompat.START)
            } else {
                drawer_layout.closeDrawers()
            }
        }
    }

    private fun extractLessonNumber(fullLessonName: String): Int {
        val indexOfSpace = fullLessonName.indexOf(" ")
        return fullLessonName.substring(indexOfSpace + 1).toInt()
    }
}

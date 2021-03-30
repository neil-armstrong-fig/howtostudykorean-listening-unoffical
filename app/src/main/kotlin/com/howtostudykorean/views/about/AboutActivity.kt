package com.howtostudykorean.views.about

import android.content.Intent
import android.os.Bundle
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.howtostudykorean.R
import com.howtostudykorean.android.BaseActivity
import com.howtostudykorean.android.NoViewModel
import kotlinx.android.synthetic.main.about_page.aboutLicenseButton

class AboutActivity : BaseActivity<NoViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.about_page)

        aboutLicenseButton.setOnClickListener {
            startActivity(Intent(this, OssLicensesMenuActivity::class.java))
        }
    }
}
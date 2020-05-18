package com.vakk.myapplication.ui.main

import android.content.Context
import com.vakk.common.cast
import com.vakk.myapplication.App
import com.vakk.myapplication.R
import com.vakk.starter.ui.BaseActivity
import com.vakk.starter.utils.LocaleUtils

class MainActivity : BaseActivity<MainViewModel>(
    MainViewModel::class.java,
    R.layout.activity_main
) {
    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            val context = LocaleUtils.wrap(it, it.applicationContext.cast<App>().language?.iso3166 ?: "EN")
            super.attachBaseContext(context)
        }
    }
}

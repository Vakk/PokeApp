package com.vakk.myapplication

import com.vakk.myapplication.di.DaggerAppComponent
import com.vakk.starter.BaseDaggerApplication

class App : BaseDaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .factory()
            .create(this).apply {
                inject(this@App)
            }
    }
}
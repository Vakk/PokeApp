package com.vakk.myapplication

import android.app.Application
import com.vakk.myapplication.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    protected lateinit var injector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = injector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .factory()
            .create(this)
            .inject(this)
    }

}
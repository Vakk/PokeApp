package com.vakk.myapplication

import android.app.Application
import com.vakk.core.ApiConfig
import com.vakk.core.LanguageOwner
import com.vakk.domain.entity.Language
import com.vakk.myapplication.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector, LanguageOwner {

    @Inject
    protected lateinit var injector: DispatchingAndroidInjector<Any>

    override var language: Language? = null

    override fun androidInjector(): AndroidInjector<Any> = injector

    override fun onCreate() {
        super.onCreate()
        ApiConfig.instance = ApiConfigImpl()

        DaggerAppComponent
            .factory()
            .create(this)
            .inject(this)
    }

}
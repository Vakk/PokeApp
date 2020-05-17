package com.vakk.myapplication.di

import android.app.Application
import android.content.Context
import com.vakk.core.di.NetworkModule
import com.vakk.myapplication.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * This is the core component of DI. Subcomponents will be created from AppModule using
 * ContributesAndroidInjector and scoped modules.
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(application: App)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}
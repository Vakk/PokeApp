package com.vakk.myapplication.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component

/**
 * This is the core component of DI. Subcomponents will be created from AppModule using
 * ContributesAndroidInjector and scoped modules.
 */
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(application: Application)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}
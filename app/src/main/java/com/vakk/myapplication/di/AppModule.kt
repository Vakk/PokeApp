package com.vakk.myapplication.di

import com.vakk.myapplication.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Represents application module with contributors for activities. Each sub module will provide
 * injectors for nested components (like fragments).
 */
@Module
interface AppModule {

    @ContributesAndroidInjector(modules = [SessionModule::class])
    fun mainActivity(): MainActivity

}
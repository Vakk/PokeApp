package com.vakk.myapplication.di

import com.vakk.myapplication.di.session.SessionModule
import com.vakk.myapplication.di.session.SessionScope
import com.vakk.myapplication.ui.main.MainActivity
import com.vakk.starter.di.mvvm.ViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Represents application module with contributors for activities. Each sub module will provide
 * injectors for nested components (like fragments).
 */
@Module(includes = [ViewModelModule::class])
interface AppModule {

    @SessionScope
    @ContributesAndroidInjector(modules = [SessionModule::class])
    fun mainActivity(): MainActivity

}
package com.vakk.myapplication.di

import com.vakk.myapplication.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppModule {

    @ContributesAndroidInjector(modules = [SessionModule::class])
    fun mainActivity(): MainActivity

}
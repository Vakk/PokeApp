package com.vakk.myapplication.di

import com.vakk.starter.di.mvvm.ViewModelModule
import dagger.Module

/**
 * This module contains submodules required for
 */
@Module(includes = [ViewModelModule::class])
class SessionModule {

}
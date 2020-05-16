package com.vakk.starter.di

import com.vakk.starter.BaseDaggerApplication
import dagger.Component

@Component
interface AppComponent {
    fun inject(application: BaseDaggerApplication)
}
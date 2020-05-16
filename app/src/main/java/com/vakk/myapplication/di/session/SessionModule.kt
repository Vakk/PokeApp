package com.vakk.myapplication.di.session

import com.vakk.core.usecase.GetPokemonsUseCaseImpl
import com.vakk.domain.usecase.GetPokemonsUseCase
import com.vakk.starter.di.mvvm.ViewModelModule
import dagger.Binds
import dagger.Module

/**
 * This module contains submodules required for
 */
@Module(includes = [ViewModelModule::class, SessionModule.UseCasesModule::class])
class SessionModule {

    @Module
    abstract class UseCasesModule {
        @Binds
        abstract fun getPokemonsUseCase(userCase: GetPokemonsUseCaseImpl): GetPokemonsUseCase
    }
}
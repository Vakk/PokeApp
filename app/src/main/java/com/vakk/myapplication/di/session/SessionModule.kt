package com.vakk.myapplication.di.session

import androidx.lifecycle.ViewModel
import com.vakk.core.usecase.GetPokemonsUseCaseImpl
import com.vakk.domain.usecase.GetPokemonsUseCase
import com.vakk.myapplication.ui.main.MainViewModel
import com.vakk.myapplication.ui.pokemons.PokemonsListFragment
import com.vakk.myapplication.ui.pokemons.PokemonsListViewModel
import com.vakk.starter.di.mvvm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * This module contains submodules required for session flow.
 */
@Module(
    includes = [
        SessionModule.UseCasesModule::class,
        SessionModule.ViewModelModule::class
    ]
)
abstract class SessionModule {

    @ContributesAndroidInjector
    abstract fun pokemonsListFragment(): PokemonsListFragment

    @Module
    abstract class UseCasesModule {
        @Binds
        abstract fun getPokemonsUseCase(userCase: GetPokemonsUseCaseImpl): GetPokemonsUseCase
    }

    @Module
    abstract class ViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(PokemonsListViewModel::class)
        abstract fun pokemonsListViewModel(viewModel: PokemonsListViewModel): ViewModel

    }
}
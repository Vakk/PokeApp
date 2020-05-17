package com.vakk.myapplication.di.session

import androidx.lifecycle.ViewModel
import com.vakk.core.repository.PokemonsRepository
import com.vakk.core.repository.PokemonsRepositoryImpl
import com.vakk.core.usecase.GetPokemonsUseCaseImpl
import com.vakk.domain.usecase.GetPokemonsUseCase
import com.vakk.myapplication.ui.main.MainViewModel
import com.vakk.myapplication.ui.pokemons.PokemonsListFragment
import com.vakk.myapplication.ui.pokemons.PokemonsListViewModel
import com.vakk.network.datasource.PokeApiDatasource
import com.vakk.network.service.PokemonsApiService
import com.vakk.starter.di.mvvm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

/**
 * This module contains submodules required for session flow.
 */
@Module(
    includes = [
        SessionModule.UseCasesModule::class,
        SessionModule.ViewModelModule::class,
        SessionModule.NetworkModule::class,
        SessionModule.RepositoryModule::class
    ]
)
abstract class SessionModule {

    @ContributesAndroidInjector
    abstract fun pokemonsListFragment(): PokemonsListFragment

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

    @Module
    abstract class UseCasesModule {
        @SessionScope
        @Binds
        abstract fun getPokemonsUseCase(useCase: GetPokemonsUseCaseImpl): GetPokemonsUseCase
    }

    @Module
    class RepositoryModule {
        @SessionScope
        @Provides
        fun pokemonsRepository(
            datasource: PokeApiDatasource
        ): PokemonsRepository = PokemonsRepositoryImpl(
            datasource = datasource,
            dispatcher = Dispatchers.Default
        )
    }

    @Module
    class NetworkModule {
        @SessionScope
        @Provides
        fun pokemonsApiService(retrofit: Retrofit): PokemonsApiService {
            return retrofit.create(PokemonsApiService::class.java)
        }

        @SessionScope
        @Provides
        fun pokeApiDatasource(apiService: PokemonsApiService): PokeApiDatasource {
            return PokeApiDatasource(apiService)
        }
    }
}
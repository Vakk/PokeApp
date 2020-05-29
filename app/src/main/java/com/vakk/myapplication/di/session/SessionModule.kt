package com.vakk.myapplication.di.session

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.vakk.core.mapper.GetPokemonInfoBeanListToPokemonDtoMapper
import com.vakk.core.repository.LanguageRepository
import com.vakk.core.repository.PokemonSpritesRepository
import com.vakk.core.repository.PokemonsRepository
import com.vakk.core.usecase.GetAvaialbleLanguagesUseCaseImpl
import com.vakk.core.usecase.GetPokemonDetailsUseCaseImpl
import com.vakk.core.usecase.GetPokemonsUseCaseImpl
import com.vakk.core.usecase.SaveLanguageUseCaseImpl
import com.vakk.domain.usecase.GetAvailableLanguagesUseCase
import com.vakk.domain.usecase.GetPokemonDetailsUseCase
import com.vakk.domain.usecase.GetPokemonsUseCase
import com.vakk.domain.usecase.SaveLanguageUseCase
import com.vakk.language.LanguageDao
import com.vakk.myapplication.database.AppDatabase
import com.vakk.myapplication.ui.details.DetailsFragment
import com.vakk.myapplication.ui.details.DetailsViewModel
import com.vakk.myapplication.ui.main.MainViewModel
import com.vakk.myapplication.ui.pokemons.PokemonsListFragment
import com.vakk.myapplication.ui.pokemons.PokemonsListViewModel
import com.vakk.myapplication.ui.settings.SettingsFragment
import com.vakk.myapplication.ui.settings.SettingsViewModel
import com.vakk.myapplication.ui.timer.TimerFragment
import com.vakk.myapplication.ui.timer.TimerViewModel
import com.vakk.network.datasource.LanguagesDataSource
import com.vakk.network.datasource.PokeApiDatasource
import com.vakk.network.service.LanguagesApiService
import com.vakk.network.service.PokemonsApiService
import com.vakk.pokemon_details.PokemonDetailsDao
import com.vakk.sprites.SpritesDao
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

    @ContributesAndroidInjector
    abstract fun detailsFragment(): DetailsFragment

    @ContributesAndroidInjector
    abstract fun settingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun timerFragment(): TimerFragment

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

        @Binds
        @IntoMap
        @ViewModelKey(DetailsViewModel::class)
        abstract fun detailsViewModel(viewModel: DetailsViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(SettingsViewModel::class)
        abstract fun settingsViewModel(viewModel: SettingsViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(TimerViewModel::class)
        abstract fun timerViewModel(viewModel: TimerViewModel): ViewModel
    }

    @Module
    abstract class UseCasesModule {
        @SessionScope
        @Binds
        abstract fun getPokemonsUseCase(useCase: GetPokemonsUseCaseImpl): GetPokemonsUseCase

        @SessionScope
        @Binds
        abstract fun getPokemonDetailsUseCase(useCase: GetPokemonDetailsUseCaseImpl): GetPokemonDetailsUseCase

        @SessionScope
        @Binds
        abstract fun saveLanguageUseCase(useCase: SaveLanguageUseCaseImpl): SaveLanguageUseCase

        @SessionScope
        @Binds
        abstract fun getAvaialbleLanguagesUseCaseImpl(useCase: GetAvaialbleLanguagesUseCaseImpl): GetAvailableLanguagesUseCase

    }

    @Module
    class RepositoryModule {
        @SessionScope
        @Provides
        fun pokemonsRepository(
            datasource: PokeApiDatasource,
            pokemonDetailsDao: PokemonDetailsDao,
            getPokemonInfoBeanListToPokemonDtoMapper: GetPokemonInfoBeanListToPokemonDtoMapper
        ): PokemonsRepository = PokemonsRepository(
            datasource = datasource,
            dispatcher = Dispatchers.Default,
            database = pokemonDetailsDao,
            getPokemonInfoBeanListToPokemonDtoMapper = getPokemonInfoBeanListToPokemonDtoMapper
        )

        @SessionScope
        @Provides
        fun pokemonSpritesRepository(
            spritesDao: SpritesDao
        ): PokemonSpritesRepository {
            return PokemonSpritesRepository(spritesDao)
        }

        @SessionScope
        @Provides
        fun languageRepository(
            languagesDataSource: LanguagesDataSource,
            languageDao: LanguageDao
        ): LanguageRepository {
            return LanguageRepository(
                languagesDataSource,
                languageDao = languageDao
            )
        }

        @SessionScope
        @Provides
        fun pokemonDetailsDao(appDatabase: AppDatabase): PokemonDetailsDao {
            return appDatabase.pokemonDetailsDao()
        }

        @SessionScope
        @Provides
        fun spritesDao(appDatabase: AppDatabase): SpritesDao {
            return appDatabase.spritesDao()
        }

        @SessionScope
        @Provides
        fun languagesDao(appDatabase: AppDatabase): LanguageDao {
            return appDatabase.languageDao()
        }

        @SessionScope
        @Provides
        fun appDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "PokemonsApp"
            ).build()
        }
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
        fun languagesApiService(retrofit: Retrofit): LanguagesApiService {
            return retrofit.create(LanguagesApiService::class.java)
        }

        @SessionScope
        @Provides
        fun pokeApiDatasource(apiService: PokemonsApiService): PokeApiDatasource {
            return PokeApiDatasource(apiService)
        }

        @SessionScope
        @Provides
        fun languagesDataSource(apiService: LanguagesApiService): LanguagesDataSource {
            return LanguagesDataSource(apiService)
        }
    }
}
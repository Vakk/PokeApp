package com.vakk.myapplication.ui.details

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vakk.common.cast
import com.vakk.common.launchSafe
import com.vakk.domain.entity.PokemonDetails
import com.vakk.domain.entity.Sprites
import com.vakk.domain.usecase.GetPokemonDetailsUseCase
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    context: Context,
    val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : AndroidViewModel(context.cast()) {

    val onDetailsLoaded = MutableLiveData<PokemonDetails>()

    val onSpritesLoaded = MutableLiveData<List<SpriteItem>>()

    var pokemonId: Long? = null
        set(value) {
            if (field == null && value != null) {
                field = value
                loadPokemonInfo(value)
            }
        }

    private fun loadPokemonInfo(pokemonId: Long) = viewModelScope.launchSafe {
        val details = getPokemonDetailsUseCase(pokemonId)
        onDetailsLoaded.value = details ?: return@launchSafe
        onSpritesLoaded.value = prepareSprites(details.sprites)
    }

    fun prepareSprites(sprites: Sprites): List<SpriteItem> {
        val result = mutableListOf<SpriteItem>()
        sprites.backDefault?.let {
            result.add(
                SpriteItem(
                    title = "Back Default",
                    url = it
                )
            )
        }
        sprites.backFemale?.let {
            result.add(
                SpriteItem(
                    title = "Back Female",
                    url = it
                )
            )
        }
        sprites.backShiny?.let {
            result.add(
                SpriteItem(
                    title = "Back Shiny",
                    url = it
                )
            )
        }
        sprites.backShinyFemale?.let {
            result.add(
                SpriteItem(
                    title = "Back Shiny Female",
                    url = it
                )
            )
        }
        sprites.frontDefault?.let {
            result.add(
                SpriteItem(
                    title = "Front default",
                    url = it
                )
            )
        }
        sprites.frontFemale?.let {
            result.add(
                SpriteItem(
                    title = "Front female",
                    url = it
                )
            )
        }
        sprites.frontShiny?.let {
            result.add(
                SpriteItem(
                    title = "Front shiny",
                    url = it
                )
            )
        }
        sprites.frontShinyFemale?.let {
            result.add(
                SpriteItem(
                    title = "Front shiny female",
                    url = it
                )
            )
        }
        return result
    }
}
package com.vakk.core.mapper

import com.vakk.network.bean.GetPokemonInfoBean
import com.vakk.pokemon_details.PokemonDetailsDto
import javax.inject.Inject

class GetPokemonInfoBeanListToPokemonDtoMapper @Inject constructor() {
    operator fun invoke(beans: List<GetPokemonInfoBean>): List<PokemonDetailsDto> {
        return beans.map {
            PokemonDetailsDto(
                id = it.id,
                name = it.name,
                sprites = it.id,
                height = it.height,
                order = it.order,
                abilities = it.abilities.mapNotNull { it.ability.name },
                baseExperience = it.baseExperience,
                forms = it.forms.mapNotNull { it.name },
                gameIndices = it.gameIndices.mapNotNull { it.version.name },
                heldItems = it.heldItems.mapNotNull { it.name },
                isDefault = it.isDefault,
                locationAreaEncounters = it.locationAreaEncounters,
                moves = it.moves.mapNotNull { it.move.name },
                species = it.species.url ?: "",
                stats = it.stats.mapNotNull { it.stat.name },
                types = it.types.mapNotNull { it.type.name },
                weight = it.weight
            )
        }
    }
}
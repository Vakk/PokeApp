package com.vakk.network.bean

import com.fasterxml.jackson.annotation.JsonProperty

data class GetPokemonInfoBean(
    @JsonProperty("abilities") val abilities: List<AbilityInfo>,
    @JsonProperty("base_experience") val baseExperience: Int,
    @JsonProperty("forms") val forms: List<ProtoInfoBean>,
    @JsonProperty("game_indices") val gameIndices: List<GameIndex>,
    @JsonProperty("height") val height: Int,
    @JsonProperty("id") val id: Long,
    @JsonProperty("is_default") val isDefault: Boolean,
    @JsonProperty("location_area_encounters") val locationAreaEncounters: String,
    @JsonProperty("moves") val moves: List<Move>,
    @JsonProperty("name") val name: String,
    @JsonProperty("order") val order: Int,
    @JsonProperty("species") val species: ProtoInfoBean,
    @JsonProperty("sprites") val sprites: Sprites,
    @JsonProperty("stats") val stats: List<Stats>,
    @JsonProperty("types") val types: List<Type>,
    @JsonProperty("weight") val weight: Int
) {
    data class AbilityInfo(
        @JsonProperty("ability") val ability: ProtoInfoBean,
        @JsonProperty("is_hidden") val isHidden: Boolean,
        @JsonProperty("slot") val slot: Int
    )

    data class GameIndex(
        @JsonProperty("game_index") val gameIndex: Int,
        @JsonProperty("version") val version: ProtoInfoBean
    )

    data class Move(
        @JsonProperty("move") val move: ProtoInfoBean,
        @JsonProperty("version_group_details") val versionGroupDetails: VersionGroupDetails
    ) {
        data class VersionGroupDetails(
            @JsonProperty("level_learned_at") val levelLearnedAt: Int,
            @JsonProperty("move_learn_method") val moveLearnMethod: ProtoInfoBean
        )
    }

    data class Sprites(
        @JsonProperty("back_default") val backDefault: String?,
        @JsonProperty("back_female") val backFemale: String?,
        @JsonProperty("back_shiny") val backShiny: String?,
        @JsonProperty("back_shiny_female") val backShinyFemale: String?,
        @JsonProperty("front_default") val frontDefault: String?,
        @JsonProperty("front_female") val frontFemale: String?,
        @JsonProperty("front_shiny") val frontShiny: String?,
        @JsonProperty("front_shiny_female") val frontShinyFemale: String?
    )

    data class Stats(
        @JsonProperty("base_stat") val baseStat: Int,
        @JsonProperty("effort") val effort: Int,
        @JsonProperty("stat") val stat: ProtoInfoBean
    )

    data class Type(
        @JsonProperty("slot") val slot: Int,
        @JsonProperty("type") val type: ProtoInfoBean
    )
}
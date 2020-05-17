package com.vakk.network.bean

import com.fasterxml.jackson.annotation.JsonProperty

data class GetPokemonsBean(
    @JsonProperty("count") val count: Int,
    @JsonProperty("next") val next: String?,
    @JsonProperty("previous") val previous: String?,
    @JsonProperty("results")
    val results: List<ProtoInfoBean>
)
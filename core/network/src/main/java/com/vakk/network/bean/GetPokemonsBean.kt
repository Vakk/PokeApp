package com.vakk.network.bean

import com.fasterxml.jackson.annotation.JsonProperty

data class GetPokemonsBean(
    @JsonProperty("results")
    val results: List<ProtoInfoBean>
)
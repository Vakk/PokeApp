package com.vakk.network.bean

import com.fasterxml.jackson.annotation.JsonProperty

data class GetLanguageBean(
    @JsonProperty("id") val id: Long,
    @JsonProperty("iso3166") val iso3166: String,
    @JsonProperty("iso639") val iso639: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("names") val names: List<Name>,
    @JsonProperty("official") val official: Boolean
) {
    data class Name(
        @JsonProperty("language") val languagesProtoBean: ProtoInfoBean?,
        @JsonProperty("name") val name: String
    )
}
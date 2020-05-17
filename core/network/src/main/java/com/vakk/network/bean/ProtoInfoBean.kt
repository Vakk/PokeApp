package com.vakk.network.bean

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This is the common api data representation which used for most cases of data response.
 */
data class ProtoInfoBean(
    @JsonProperty("name") val name: String?,
    @JsonProperty("url") val url: String?
)
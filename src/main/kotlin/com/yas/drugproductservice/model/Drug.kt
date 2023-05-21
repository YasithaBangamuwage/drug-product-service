package com.yas.drugproductservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Drug(
    @JsonProperty("id") val id: String, @JsonProperty("name") val name: String,
    @JsonProperty("unit") val unit: String, @JsonProperty("publish") val publish: Boolean
) {
}
package com.gamewar.cryptoapi.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.gamewar.cryptoapi.models.entities.NewsData

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "Data")
    val `data`: List<NewsData>?,
    @Json(name = "Message")
    val message: String?,
    @Json(name = "Promoted")
    val promoted: List<Any>?,
    @Json(name = "Type")
    val type: Int?
)
package hr.codetome.starwars.model

import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    @SerializedName("title")
    val title: String
)

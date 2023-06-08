package hr.codetome.starwars.model

import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Character>
)

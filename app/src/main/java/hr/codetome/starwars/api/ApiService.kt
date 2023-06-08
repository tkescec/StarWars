package hr.codetome.starwars.api

import hr.codetome.starwars.model.FilmResponse
import hr.codetome.starwars.model.HomeWorldResponse
import hr.codetome.starwars.model.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("people/?page/")
    suspend fun getCharacters(@Query("page") page: Int): PeopleResponse

    @GET
    suspend fun getFilm(@Url url: String): FilmResponse

    @GET
    suspend fun getHomeWorld(@Url url: String): HomeWorldResponse
}
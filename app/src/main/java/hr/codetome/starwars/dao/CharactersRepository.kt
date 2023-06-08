package hr.codetome.starwars.dao

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import hr.codetome.starwars.api.ApiService
import hr.codetome.starwars.api.SafeApiCall
import hr.codetome.starwars.model.Character
import hr.codetome.starwars.utils.Constants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val apiService: ApiService) : SafeApiCall() {

    fun getCharacters(searchString: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiService, searchString)
            }
        ).flow
    }

    suspend fun getFilm(url: String) = safeApiCall {
        apiService.getFilm(url)
    }

    suspend fun getHomeWorld(url: String) = safeApiCall {
        apiService.getHomeWorld(url)
    }
}
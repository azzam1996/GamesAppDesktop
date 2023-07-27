package domain.repository

import domain.model.Game
import domain.model.GameDetails
import domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun getGames(): Flow<Resource<List<Game?>?>>
    suspend fun getGameDetails(id: Int): Flow<Resource<GameDetails?>>
}
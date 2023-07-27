package data.repository

import data.mappers.toGame
import data.mappers.toGameDetails
import data.remote.ClientApi
import domain.model.Game
import domain.model.GameDetails
import domain.repository.GamesRepository
import domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GamesRepositoryImpl(private val api: ClientApi) : GamesRepository {
    override suspend fun getGames(): Flow<Resource<List<Game?>?>> = flow {
        emit(Resource.Loading())
        try {
            val gamesFromApi = api.getGames()
            val games = gamesFromApi?.map { gameDto ->
                gameDto?.let {
                    it.toGame()
                }
            }
            emit(Resource.Success(games))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Unknown Error"))
        }
    }

    override suspend fun getGameDetails(id: Int): Flow<Resource<GameDetails?>> = flow {
        emit(Resource.Loading())
        try {
            val gameDetailsFromApi = api.getGameDetails(id)
            emit(Resource.Success(gameDetailsFromApi?.toGameDetails()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Unknown Error"))
        }
    }

}
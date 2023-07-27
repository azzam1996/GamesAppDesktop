package data.remote

import data.remote.dto.GameDetailsDto
import data.remote.dto.GameDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ClientApi(private val client: HttpClient): Api {

    override suspend fun getGames(): List<GameDto?>? {
        return client.get {
            url("https://www.freetogame.com/api/games")
        }.body()

    }

    override suspend fun getGameDetails(id: Int): GameDetailsDto? {
        return client.get {
            url("https://www.freetogame.com/api/game?id=${id}")
        }.body()
    }
}
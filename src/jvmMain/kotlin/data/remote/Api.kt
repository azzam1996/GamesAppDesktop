package data.remote

import data.remote.dto.GameDetailsDto
import data.remote.dto.GameDto

interface Api {
    suspend fun getGames(): List<GameDto?>?
    suspend fun getGameDetails(id:Int): GameDetailsDto?
}
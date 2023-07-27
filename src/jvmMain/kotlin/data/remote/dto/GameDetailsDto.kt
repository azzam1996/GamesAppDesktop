package data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsDto(
    @SerialName("description") val description: String? = null,
    @SerialName("developer") val developer: String? = null,
    @SerialName("freetogame_profile_url") val freeToGameProfileUrl: String? = null,
    @SerialName("game_url") val gameUrl: String? = null,
    @SerialName("genre") val genre: String? = null,
    @SerialName("id") val id: Int,
    @SerialName("minimum_system_requirements") val minimumSystemRequirements: MinimumSystemRequirementsDto? = null,
    @SerialName("platform") val platform: String? = null,
    @SerialName("publisher") val publisher: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("screenshots") val screenshots: List<ScreenShotDto>? = null,
    @SerialName("short_description") val shortDescription: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("thumbnail") val thumbnail: String? = null,
    @SerialName("title") val title: String? = null
)

@Serializable
data class MinimumSystemRequirementsDto(
    @SerialName("graphics") val graphics: String? = null,
    @SerialName("memory") val memory: String? = null,
    @SerialName("os") val os: String? = null,
    @SerialName("processor") val processor: String? = null,
    @SerialName("storage") val storage: String? = null
)

@Serializable
data class ScreenShotDto(
    @SerialName("image") val image: String? = null,
    @SerialName("id") val id: Int? = null,
)
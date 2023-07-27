package data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    @SerialName("developer") val developer: String? = null,
    @SerialName("freetogame_profile_url") val freeToGameProfileUrl: String? = null,
    @SerialName("game_url") val gameUrl: String? = null,
    @SerialName("genre") val genre: String? = null,
    @SerialName("id") val id: Int,
    @SerialName("platform") val platform: String? = null,
    @SerialName("publisher") val publisher: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("short_description") val shortDescription: String? = null,
    @SerialName("thumbnail") val thumbnail: String? = null,
    @SerialName("title") val title: String? = null
)
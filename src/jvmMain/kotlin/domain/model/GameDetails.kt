package domain.model

data class GameDetails(
    val description: String? = null,
    val developer: String? = null,
    val freeToGameProfileUrl: String? = null,
    val gameUrl: String? = null,
    val genre: String? = null,
    val id: Int,
    val minimumSystemRequirements: MinimumSystemRequirements? = null,
    val platform: String? = null,
    val publisher: String? = null,
    val releaseDate: String? = null,
    val screenshots: List<ScreenShot>? = null,
    val shortDescription: String? = null,
    val status: String? = null,
    val thumbnail: String? = null,
    val title: String? = null
)


data class MinimumSystemRequirements(
    val graphics: String? = null,
    val memory: String? = null,
    val os: String? = null,
    val processor: String? = null,
    val storage: String? = null
)


data class ScreenShot(
    val image: String? = null,
    val id: Int? = null,
)
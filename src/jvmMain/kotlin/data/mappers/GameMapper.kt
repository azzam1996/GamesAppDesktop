package data.mappers

import data.remote.dto.GameDetailsDto
import data.remote.dto.GameDto
import data.remote.dto.MinimumSystemRequirementsDto
import data.remote.dto.ScreenShotDto
import domain.model.Game
import domain.model.GameDetails
import domain.model.MinimumSystemRequirements
import domain.model.ScreenShot


fun GameDto.toGame(): Game {
    return Game(
        developer = developer,
        freeToGameProfileUrl = freeToGameProfileUrl,
        gameUrl = gameUrl,
        genre = genre,
        id = id,
        platform = platform,
        publisher = publisher,
        releaseDate = releaseDate,
        shortDescription = shortDescription,
        thumbnail = thumbnail,
        title = title
    )
}

fun GameDetailsDto.toGameDetails(): GameDetails {
    return GameDetails(
        description = description,
        developer = developer,
        freeToGameProfileUrl = freeToGameProfileUrl,
        gameUrl = gameUrl,
        genre = genre,
        id = id,
        minimumSystemRequirements = minimumSystemRequirements?.toMinimumSystemRequirements(),
        platform = platform,
        publisher = publisher,
        releaseDate = releaseDate,
        screenshots = screenshots?.map { it.toScreenShot() },
        shortDescription = shortDescription,
        status = status,
        thumbnail = thumbnail,
        title = title
    )
}

fun ScreenShotDto.toScreenShot(): ScreenShot {
    return ScreenShot(
        image = image,
        id = id,
    )
}

fun MinimumSystemRequirementsDto.toMinimumSystemRequirements(): MinimumSystemRequirements {
    return MinimumSystemRequirements(
        graphics = graphics,
        memory = memory,
        os = os,
        processor = processor,
        storage = storage
    )
}
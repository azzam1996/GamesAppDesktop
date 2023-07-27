package presentation.games

import domain.model.Game
import domain.model.GameDetails

data class GamesListState(
    val games: List<Game?> = emptyList(),
    val isLoading: Boolean = false,
    val showTryAgainButton: Boolean = false
)

data class GameDetailsState(
    val gameDetails: GameDetails? = null,
    val isLoading: Boolean = false,
)
package presentation.games

import domain.repository.GamesRepository
import domain.util.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class GamesViewModel(private val gamesRepository: GamesRepository) {

    var gamesListState = MutableStateFlow(GamesListState())
    var gameDetailsState = MutableStateFlow(GameDetailsState())

    private var getGamesListJob: Job? = null
    private var getGameDetailsJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getGames()
    }

    fun getGames() {
        getGamesListJob?.cancel()
        getGamesListJob = CoroutineScope(Dispatchers.IO).launch {
            gamesRepository.getGames().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result?.data?.let { gamesList ->
                            gamesListState.update {
                                it.copy(games = gamesList, isLoading = false, showTryAgainButton = false)
                            }
                        }
                    }

                    is Resource.Error -> {
                        gamesListState.update { it.copy(isLoading = false, showTryAgainButton = true) }
                        _eventFlow.emit(UIEvent.ShowMessage(result.message ?: "UnKnown Error"))
                    }

                    is Resource.Loading -> {
                        gamesListState.update { it.copy(isLoading = true, showTryAgainButton = false) }
                    }
                }
            }.launchIn(this)
        }
    }

    fun getGameDetails(id: Int) {
        getGameDetailsJob?.cancel()
        getGameDetailsJob = CoroutineScope(Dispatchers.IO).launch {
            gamesRepository.getGameDetails(id).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result?.data?.let { gamesList ->
                            gameDetailsState.update { it.copy(gameDetails = gamesList, isLoading = false) }
                        }
                    }

                    is Resource.Error -> {
                        gamesListState.update { it.copy(isLoading = false) }
                        _eventFlow.emit(UIEvent.ShowMessage(result.message ?: "UnKnown Error"))
                    }

                    is Resource.Loading -> {
                        gameDetailsState.update { it.copy(isLoading = true) }
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowMessage(val message: String) : UIEvent()
    }
}
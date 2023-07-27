package presentation.games.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.Game
import presentation.games.GamesListState

@Composable
fun GamesListCard(
    gamesListState: GamesListState,
    gameClickEvent: (Game) -> Unit,
    getGames: () -> Unit,
    modifier: Modifier = Modifier
) {
    MainCard(
        modifier = modifier
    ) {
        Box {
            when (gamesListState.isLoading) {
                true -> {
                    Box(modifier = modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    when (gamesListState.showTryAgainButton) {
                        true -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {

                                CustomButton(
                                    title = "Try Again",
                                    modifier = Modifier
                                        .padding(vertical = 15.dp, horizontal = 20.dp),
                                    action = {
                                        getGames.invoke()
                                    }
                                )
                            }
                        }

                        else -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                contentPadding = PaddingValues(10.dp)
                            ) {

                                itemsIndexed(
                                    gamesListState.games,
                                    key = { _, game ->
                                        game?.id.toString()
                                    }
                                ) { _, game ->
                                    game?.let {
                                        GameListItem(
                                            game = it,
                                            gameClickEvent = { gameClickEvent.invoke(game) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



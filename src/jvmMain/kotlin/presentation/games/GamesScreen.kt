package presentation.games

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import org.koin.java.KoinJavaComponent.inject
import presentation.games.widgets.AppMainBackground
import presentation.games.widgets.GameDetailsCard
import presentation.games.widgets.GamesListCard
import presentation.theme.BlueZodiac
import javax.swing.JOptionPane

@Composable
@Preview
fun GamesScreen() {

    val viewModel by inject<GamesViewModel>(GamesViewModel::class.java)
    val gamesListState by viewModel.gamesListState.collectAsState()
    val gameDetailsState by viewModel.gameDetailsState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is GamesViewModel.UIEvent.ShowMessage -> {
                    JOptionPane.showMessageDialog(null, event.message)
                }
            }
        }
    }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BlueZodiac)
        ) {
            AppMainBackground()
            Row {
                GamesListCard(
                    gamesListState,
                    modifier = Modifier
                        .padding(20.dp)
                        .width(300.dp),
                    gameClickEvent = { game ->
                        viewModel.getGameDetails(game.id)
                    },
                    getGames = viewModel::getGames
                )

                GameDetailsCard(
                    gameDetailsState = gameDetailsState,
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp, end = 20.dp)
                        .fillMaxSize()
                )

            }
        }
    }
}
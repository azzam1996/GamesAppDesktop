
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import di.networkModule
import di.repositoryModule
import di.viewModelModule
import org.koin.core.context.startKoin
import presentation.games.GamesScreen
import java.awt.Dimension

fun main() = application {
    val state = rememberWindowState(
        placement = WindowPlacement.Maximized,
        position = WindowPosition(0.dp,0.dp),
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "Games App",
        state = state
    ) {
        startKoin {
            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
        this.window.minimumSize = Dimension(1000,600)
        GamesScreen()
    }
}

package presentation.games.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AppMainBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource("top_ellipse.png"),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopStart).offset(x = -100.dp, y = -100.dp)
        )
        Image(
            painterResource("end_ellipse.png"),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        Image(
            painterResource("bottom_end_ellipse.png"),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
        Image(
            painterResource("bottom_start_ellipse.png"),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}
package presentation.games.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.Game

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameListItem(
    game: Game,
    gameClickEvent: (Game) -> Unit
) {
    var backgroundColor by remember { mutableStateOf(Color.Transparent) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .border(BorderStroke(width = 1.dp, color = Color.White), shape = RoundedCornerShape(20.dp))
        .background(backgroundColor)
        .onPointerEvent(PointerEventType.Enter) { backgroundColor = Color.White.copy(alpha = 0.15f) }
        .onPointerEvent(PointerEventType.Exit) { backgroundColor = Color.Transparent }
        .clickable {
            gameClickEvent.invoke(game)
        }
    ) {
        Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)) {
            Spacer(modifier = Modifier.weight(1f))

            UrlImage(
                url = game.thumbnail ?: "No DATA",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 10.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                game.title ?: "No DATA",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()

            )

            Text(
                game.genre ?: "No DATA",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
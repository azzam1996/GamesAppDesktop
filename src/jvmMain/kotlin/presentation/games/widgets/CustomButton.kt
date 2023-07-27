package presentation.games.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.theme.PersianRose
import presentation.theme.ScienceBlue

@Composable
fun CustomButton(
    title: String,
    modifier: Modifier = Modifier,
    action: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        PersianRose,
                        ScienceBlue,
                    )
                )
            )
            .clickable {
                action.invoke()
            },
    ) {
        Text(
            title,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 15.dp, horizontal = 20.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
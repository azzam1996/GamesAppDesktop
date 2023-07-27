package presentation.games.widgets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.games.GameDetailsState
import java.awt.Desktop
import java.net.URI
import javax.swing.JOptionPane

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameDetailsCard(
    gameDetailsState: GameDetailsState,
    modifier: Modifier = Modifier
) {
    MainCard(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (gameDetailsState.isLoading) {
                true -> {
                    Box(modifier = modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    val gameDetails = gameDetailsState.gameDetails

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {

                        Box(
                            modifier = Modifier
                                .padding(all = 20.dp)
                                .fillMaxWidth()
                                .height(400.dp)
                        ) {
                            when ((gameDetails?.screenshots?.size ?: 0) > 0) {
                                true -> {
                                    AutoSlidingCarousel(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(20.dp))
                                            .border(
                                                BorderStroke(width = 1.dp, color = Color.White),
                                                shape = RoundedCornerShape(20.dp)
                                            ),
                                        itemsCount = gameDetails?.screenshots?.size ?: 0,
                                        data = gameDetails?.screenshots ?: emptyList()
                                    )

                                    FloatingCard(
                                        modifier = Modifier
                                            .padding(start = 20.dp, bottom = 20.dp, end = 20.dp)
                                            .align(Alignment.BottomCenter)
                                            .fillMaxWidth()
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(
                                                    brush = Brush.verticalGradient(
                                                        listOf(
                                                            Color.Transparent,
                                                            Color.Black.copy(alpha = 0.4f),
                                                        )
                                                    )
                                                )
                                                .padding(20.dp)
                                        ) {
                                            Text(
                                                gameDetails?.title ?: "NO DATA",
                                                color = Color.White,
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Start,
                                                modifier = Modifier
                                                    .padding(bottom = 10.dp)
                                            )
                                            Text(
                                                gameDetails?.shortDescription ?: "No DATA",
                                                color = Color.White,
                                                textAlign = TextAlign.Start,
                                            )
                                        }
                                    }
                                }

                                else -> {
                                    Image(
                                        painter = painterResource("placeholder.jpg"),
                                        contentScale = ContentScale.FillBounds,
                                        contentDescription = "null",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(20.dp))
                                            .border(
                                                BorderStroke(width = 1.dp, color = Color.White),
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                    )

                                }
                            }
                        }

                        MainCard(
                            modifier = Modifier
                                .padding(start = 20.dp, bottom = 20.dp, end = 20.dp)
                                .fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                            {
                                Column {
                                    Text(
                                        "Description : ",
                                        color = Color.White,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                    )
                                    Text(
                                        gameDetails?.description ?: "No DATA",
                                        color = Color.White,
                                        textAlign = TextAlign.Start,
                                    )
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .height(IntrinsicSize.Min)
                        ) {
                            MainCard(
                                modifier = Modifier
                                    .padding(start = 20.dp, bottom = 20.dp, end = 10.dp)
                                    .fillMaxHeight()
                                    .weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxSize()
                                ) {
                                    Column {
                                        Text(
                                            "System Requirements : ",
                                            color = Color.White,
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .padding(bottom = 10.dp)
                                        )
                                        LabelValueItem("OS", gameDetails?.minimumSystemRequirements?.os)
                                        LabelValueItem("Processor", gameDetails?.minimumSystemRequirements?.processor)
                                        LabelValueItem("Memory", gameDetails?.minimumSystemRequirements?.memory)
                                        LabelValueItem("Graphics", gameDetails?.minimumSystemRequirements?.graphics)
                                        LabelValueItem("Storage", gameDetails?.minimumSystemRequirements?.storage)
                                    }
                                }
                            }

                            MainCard(
                                modifier = Modifier
                                    .padding(start = 10.dp, bottom = 20.dp, end = 20.dp)
                                    .fillMaxHeight()
                                    .weight(1f)

                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(10.dp)
                                )
                                {
                                    Column {
                                        LabelValueItem("Platform", gameDetails?.platform)
                                        LabelValueItem("Publisher", gameDetails?.publisher)
                                        LabelValueItem("Developer", gameDetails?.developer)
                                        LabelValueItem("Release Date", gameDetails?.releaseDate)

                                        Spacer(modifier = Modifier.weight(1f))

                                        CustomButton(
                                            title = "Play NOW",
                                            action = {
                                                try {
                                                    val desktop =
                                                        if (Desktop.isDesktopSupported()) Desktop.getDesktop() else null
                                                    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                                                        desktop?.browse(URI(gameDetails?.freeToGameProfileUrl))
                                                    }
                                                } catch (e: Exception) {
                                                    JOptionPane.showMessageDialog(null, "Can't Open Link")
                                                }
                                            }
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
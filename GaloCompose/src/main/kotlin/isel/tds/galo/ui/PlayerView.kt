package isel.tds.galo.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import isel.tds.galo.model.Player

@Composable
fun PlayerView(
    player: Player?,
    size: Dp = 100.dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val modifier = modifier.size(size)
    if (player == null) {
        Box(
            modifier = modifier
                .clickable(onClick = onClick )
        )
    } else {
        val filename = when (player) {
            Player.X -> "cross"
            Player.O -> "circle"
        }
        Image(
            painter = painterResource("$filename.png"),
            contentDescription = null,
            modifier = modifier
        )
    }
}

@Composable
@Preview
fun PlayerViewPreview() {
    Column(modifier = Modifier.background(Color.Black)) {
        PlayerView(null, 100.dp, Modifier.background(Color.Red))
        PlayerView(Player.X, 50.dp)
        PlayerView(Player.O, 25.dp)
    }
}
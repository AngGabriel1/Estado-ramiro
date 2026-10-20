package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MiCard(
    modifier: Modifier = Modifier,
    nombre: String,
    color: String
) {

    val colorFondo = when (color) {

        "rojo" -> Color.Red

        "azul" -> Color.Blue

        "verde" -> Color.Green

        "amarillo" -> Color.Yellow

        else -> Color.White
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colorFondo)
            .padding(20.dp)
    ) {

        Text(
            text = nombre,
            fontSize = 18.sp
        )
    }
}
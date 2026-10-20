package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MiCard(
    modifier: Modifier = Modifier,
    nombre: String,
    onEliminar: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(2.dp, Color.Blue),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = nombre,
                color = Color.Black
            )

            Text(
                text = "X",
                color = Color.Red,
                modifier = Modifier.clickable {
                    onEliminar()
                }
            )
        }
    }
}

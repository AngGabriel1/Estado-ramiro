
package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.estado.R

@Composable
fun MiCard(
    nombre: String,
    color: String
) {

    val colorFondo = when (color.lowercase()) {
        "rojo" -> colorResource(R.color.rojo)
        "azul" -> colorResource(R.color.azul)
        "verde" -> colorResource(R.color.verde)
        "amarillo" -> colorResource(R.color.amarillo)
        else -> colorResource(R.color.blanco)
    }
}

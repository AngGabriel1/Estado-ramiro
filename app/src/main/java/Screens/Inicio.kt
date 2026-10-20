package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    color: String,
    colorSeleccionado: String,
    listaNombres: List<String>,
    onColorChange: (String) -> Unit,
    onCambiarColor: () -> Unit
) {

    Column(
        modifier = modifier.padding(15.dp)
    ) {

        TextField(
            value = color,
            onValueChange = onColorChange,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Escribe un color")
            }
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Button(
            onClick = onCambiarColor,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cambiar color")
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        LazyColumn {

            items(listaNombres) { itemNombre ->

                MiCard(
                    nombre = itemNombre,
                    color = colorSeleccionado,
                    modifier = Modifier.padding(
                        vertical = 4.dp
                    )
                )
            }
        }
    }
}
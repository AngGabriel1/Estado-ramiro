package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    numero1: String,
    numero2: String,
    resultado: String,
    onNumero1Change: (String) -> Unit,
    onNumero2Change: (String) -> Unit,
    onSumar: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = numero1,
            onValueChange = onNumero1Change,
            label = { Text("Primer número") }
        )

        TextField(
            value = numero2,
            onValueChange = onNumero2Change,
            label = { Text("Segundo número") }
        )

        Button(
            onClick = onSumar
        ) {
            Text("Sumar")
        }

        Text(
            text = "Resultado: $resultado"
        )
    }
}

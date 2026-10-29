
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
    numero1: String,
    numero2: String,
    resultado: String,
    onCambiarNumero1: (String) -> Unit,
    onCambiarNumero2: (String) -> Unit,
    onCambiarResultado: () -> Unit
) {

    Column(
        modifier = modifier.padding(15.dp)
    ) {

        TextField(
            value = numero1,
            onValueChange = onCambiarNumero1,
            label = {
                Text("Numero1")
            }
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        TextField(
            value = numero2,
            onValueChange = onCambiarNumero2,
            label = {
                Text("Numero2")
            }
        )


        Button(
            onClick = onCambiarResultado,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Resultado")
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )



    }
}

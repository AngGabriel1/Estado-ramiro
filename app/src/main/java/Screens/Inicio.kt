
package screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    nombre: String,
    listaNombres: List<String>,
    nombreModificar: String,
    onNombreChange: (String) -> Unit,
    onAgregar: () -> Unit,
    onModificar: (String) -> Unit,
    onGuardarCambios: () -> Unit,
    onEliminar: (String) -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        TextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre") }
        )

        Button(
            onClick = {
                if (nombreModificar == "") {
                    onAgregar()
                } else {
                    onGuardarCambios()
                }
            }
        ) {
            Text(
                if (nombreModificar == "") "Agregar"
                else "Guardar cambios"
            )
        }

        LazyColumn {
            items(listaNombres) { itemNombre ->
                MiCard(
                    modifier = Modifier.padding(vertical = 4.dp),
                    nombre = itemNombre,
                    onModificar = {
                        onModificar(itemNombre)
                    },
                    onEliminar = {
                        onEliminar(itemNombre)
                    }
                )
            }
        }
    }
}

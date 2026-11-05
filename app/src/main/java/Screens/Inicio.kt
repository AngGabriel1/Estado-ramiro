package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Movimiento(
    val id: Int,
    val descripcion: String,
    val cantidad: Double,
    val esIngreso: Boolean
)

@Composable
fun PresupuestoScreen() {

    var descripcion by remember { mutableStateOf("") }
    var cantidadTexto by remember { mutableStateOf("") }
    var esIngreso by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf("") }

    val movimientos = remember {
        mutableStateListOf<Movimiento>()
    }

    val totalIngresos = movimientos
        .filter { it.esIngreso }
        .sumOf { it.cantidad }

    val totalGastos = movimientos
        .filter { !it.esIngreso }
        .sumOf { it.cantidad }

    val saldo = totalIngresos - totalGastos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        if (quizTerminado) {
            Text(
                text = "¡Quiz terminado!",
                style = MaterialTheme.typography.headlineMedium
            )

        Text(
            text = "Mi presupuesto",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Comparte tu opinión y califica tu experiencia.")

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Saldo disponible")
                Text(
                    text = "$${"%.2f".format(saldo)}",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Ingresos: $${"%.2f".format(totalIngresos)}")
                Text("Gastos: $${"%.2f".format(totalGastos)}")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Nuevo movimiento",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = {
                descripcion = it
                error = ""
            },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = cantidadTexto,
            onValueChange = {
                cantidadTexto = it
                error = ""
            },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = esIngreso,
                onClick = { esIngreso = true }
            )
            Text("Ingreso")

            RadioButton(
                selected = !esIngreso,
                onClick = { esIngreso = false }
            )
            Text("Gasto")
        }

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                val cantidad = cantidadTexto.toDoubleOrNull()

                when {
                    descripcion.isBlank() -> {
                        error = "Escribe una descripción."
                    }

                    cantidad == null || cantidad <= 0 -> {
                        error = "Ingresa una cantidad válida."
                    }

                    else -> {
                        movimientos.add(
                            Movimiento(
                                id = (movimientos.maxOfOrNull { it.id } ?: 0) + 1,
                                descripcion = descripcion,
                                cantidad = cantidad,
                                esIngreso = esIngreso
                            )
                        )

                        descripcion = ""
                        cantidadTexto = ""
                        error = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar movimiento")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Historial",
            style = MaterialTheme.typography.titleLarge
        )

        if (movimientos.isEmpty()) {
            Text("Todavía no hay movimientos.")
        } else {
            movimientos.forEach { movimiento ->
                MovimientoItem(
                    movimiento = movimiento,
                    onEliminar = {
                        movimientos.remove(movimiento)
                    }
                )
            }
        }
    }
}

@Composable
fun MovimientoItem(
    movimiento: Movimiento,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = movimiento.descripcion,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = if (movimiento.esIngreso) {
                        "Ingreso: +$${"%.2f".format(movimiento.cantidad)}"
                    } else {
                        "Gasto: -$${"%.2f".format(movimiento.cantidad)}"
                    },
                    color = if (movimiento.esIngreso) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )
            }
        }
    }
}

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onEliminar) {
                Text("Eliminar")
            }
        }
    }
}
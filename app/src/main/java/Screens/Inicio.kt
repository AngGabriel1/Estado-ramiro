package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    personasActuales: Int,
    visitasDelDia: Int,
    porcentaje: Float,
    colorIndicador: String,
    onEntrar: () -> Unit,
    onSalir: () -> Unit,
    onCerrarDia: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Text(
            text = "Control de Aforo",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        MiCard(
            titulo = "Personas en la sala",
            contenido = "$personasActuales / 50"
        )

        Spacer(modifier = Modifier.height(16.dp))

        MiCard(
            titulo = "Visitas del día",
            contenido = visitasDelDia.toString()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ocupación: ${(porcentaje * 100).toInt()}%"
        )

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { porcentaje },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Estado: $colorIndicador"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                onClick = onEntrar,
                enabled = personasActuales < 50,
                modifier = Modifier.weight(1f)
            ) {
                Text("Entra")
            }

            Button(
                onClick = onSalir,
                enabled = personasActuales > 0,
                modifier = Modifier.weight(1f)
            ) {
                Text("Sale")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCerrarDia,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar día")
        }
    }
}
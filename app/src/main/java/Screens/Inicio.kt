
package com.example.estado.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    tablero: List<String>,
    jugador: String,
    ganador: String,
    onJugar: (Int) -> Unit,
    onReiniciar: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Juego del Gato",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Mostrar turno o ganador
        Text(
            text = when (ganador) {
                "Empate" -> "¡Es un empate!"
                "" -> "Turno del jugador: $jugador"
                else -> "¡Ganó el jugador $ganador!"
            },
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Primera fila
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MiCard(
                modifier = Modifier.weight(1f),
                valor = tablero[0],
                onClick = { onJugar(0) }
            )

            MiCard(
                modifier = Modifier.weight(1f),
                valor = tablero[1],
                onClick = { onJugar(1) }
            )

            MiCard(
                modifier = Modifier.weight(1f),
                valor = tablero[2],
                onClick = { onJugar(2) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Segunda fila
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MiCard(
                modifier = Modifier.weight(1f),
                valor = tablero[3],
                onClick = { onJugar(3) }
            )

            MiCard(
                modifier = Modifier.weight(1f),
                valor = tablero[4],
                onClick = { onJugar(4) }
            )

            MiCard(
                modifier = Modifier.weight(1f),
                valor = tablero[5],
                onClick = { onJugar(5) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Tercera fila
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MiCard(
                modifier = Modifier.weight(1f),
                valor = tablero[6],
                onClick = { onJugar(6) }
            )

            MiCard(
                modifier = Modifier.weight(1f),
                valor = tablero[7],
                onClick = { onJugar(7) }
            )

            MiCard(
                modifier = Modifier.weight(1f),
                valor = tablero[8],
                onClick = { onJugar(8) }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = onReiniciar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar partida")
        }
    }
}

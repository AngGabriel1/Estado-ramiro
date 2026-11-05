
package com.example.estado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.estado.Screens.InicioScreen
import com.example.estado.ui.theme.EstadoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EstadoTheme {

                // Variables de estado
                var tablero by remember {
                    mutableStateOf(List(9) { "" })
                }

                var jugador by remember {
                    mutableStateOf("X")
                }

                var ganador by remember {
                    mutableStateOf("")
                }

                // Función para verificar si alguien ganó
                val verificarGanador = { casillas: List<String> ->

                    val combinaciones = listOf(
                        listOf(0, 1, 2),
                        listOf(3, 4, 5),
                        listOf(6, 7, 8),
                        listOf(0, 3, 6),
                        listOf(1, 4, 7),
                        listOf(2, 5, 8),
                        listOf(0, 4, 8),
                        listOf(2, 4, 6)
                    )

                    combinaciones.any { combinacion ->
                        val a = combinacion[0]
                        val b = combinacion[1]
                        val c = combinacion[2]

                        casillas[a].isNotEmpty() &&
                                casillas[a] == casillas[b] &&
                                casillas[b] == casillas[c]
                    }
                }

                // Función para jugar
                val onJugar: (Int) -> Unit = { posicion ->

                    if (tablero[posicion].isEmpty() &&
                        ganador.isEmpty()
                    ) {

                        val nuevoTablero = tablero.toMutableList()

                        nuevoTablero[posicion] = jugador

                        tablero = nuevoTablero

                        if (verificarGanador(tablero)) {

                            ganador = jugador

                        } else if (tablero.none { it.isEmpty() }) {

                            ganador = "Empate"

                        } else {

                            jugador = if (jugador == "X") "O" else "X"
                        }
                    }
                }

                // Función para reiniciar
                val reiniciar = {
                    tablero = List(9) { "" }
                    jugador = "X"
                    ganador = ""
                }

                // Llamamos a InicioScreen
                InicioScreen(
                    modifier = Modifier.fillMaxSize(),
                    tablero = tablero,
                    jugador = jugador,
                    ganador = ganador,
                    onJugar = onJugar,
                    onReiniciar = reiniciar
                )
            }
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EstadoTheme {
        InicioScreen(
            modifier = Modifier.fillMaxSize(),
            tablero = List(9) { "" },
            jugador = "X",
            ganador = "",
            onJugar = {},
            onReiniciar = {}
        )
    }
}

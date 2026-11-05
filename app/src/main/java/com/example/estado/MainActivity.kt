package com.example.estado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.estado.Screens.InicioScreen
import com.example.estado.ui.theme.EstadoTheme

const val AFORO_MAX = 50

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EstadoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var personasActuales by remember { mutableStateOf(0) }
                    var visitasDelDia by remember { mutableStateOf(0) }

                    val porcentaje =
                        personasActuales / AFORO_MAX.toFloat()

                    val colorIndicador = when {
                        porcentaje < 0.70f -> "Verde"
                        porcentaje < 1.0f -> "Amarillo"
                        else -> "Rojo"
                    }

                    InicioScreen(
                        modifier = Modifier.padding(),
                        personasActuales = personasActuales,
                        visitasDelDia = visitasDelDia,
                        porcentaje = porcentaje,
                        colorIndicador = colorIndicador,
                        onEntrar = {
                            if (personasActuales < AFORO_MAX) {
                                personasActuales++
                                visitasDelDia++
                            }
                        },
                        onSalir = {
                            if (personasActuales > 0) {
                                personasActuales--
                            }
                        },
                        onCerrarDia = {
                            personasActuales = 0
                            visitasDelDia = 0
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    EstadoTheme {
        InicioScreen(
            modifier = Modifier,
            personasActuales = 32,
            visitasDelDia = 45,
            porcentaje = 32 / AFORO_MAX.toFloat(),
            colorIndicador = "Verde",
            onEntrar = {},
            onSalir = {},
            onCerrarDia = {}
        )
    }
}
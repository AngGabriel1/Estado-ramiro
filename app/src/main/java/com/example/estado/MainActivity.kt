
package com.example.estado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.estado.Screens.InicioScreen
import com.example.estado.ui.theme.EstadoTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EstadoTheme {

                // Variables de estado
                var numero1 by rememberSaveable {
                    mutableStateOf("")
                }

                var numero2 by rememberSaveable {
                    mutableStateOf("")
                }

                var resultado by rememberSaveable {
                    mutableStateOf("")
                }

                // Función para realizar las operaciones
                val calcular: (String) -> Unit = { operacion ->

                    val n1 = numero1.toDoubleOrNull()
                    val n2 = numero2.toDoubleOrNull()

                    if (n1 == null || n2 == null) {

                        resultado = "Ingresa dos números válidos"

                    } else {

                        val res = when (operacion) {

                            "+" -> n1 + n2

                            "-" -> n1 - n2

                            "*" -> n1 * n2

                            "/" -> {
                                if (n2 != 0.0) {
                                    n1 / n2
                                } else {
                                    null
                                }
                            }

                            "%" -> {
                                if (n2 != 0.0) {
                                    n1 % n2
                                } else {
                                    null
                                }
                            }

                            "^" -> n1.pow(n2)

                            else -> null
                        }

                        resultado = when {
                            res == null -> "No se puede dividir entre cero"

                            !res.isFinite() -> "Resultado no válido"

                            res % 1.0 == 0.0 -> res.toLong().toString()

                            else -> res.toString()
                        }
                    }
                }

                // Función para limpiar
                val limpiar = {
                    numero1 = ""
                    numero2 = ""
                    resultado = ""
                }

                // Llamamos a la pantalla
                InicioScreen(
                    modifier = Modifier.fillMaxSize(),
                    numero1 = numero1,
                    numero2 = numero2,
                    resultado = resultado,

                    onNumero1Change = {
                        numero1 = it
                    },

                    onNumero2Change = {
                        numero2 = it
                    },

                    onCalcular = calcular,
                    onLimpiar = limpiar
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
            numero1 = "",
            numero2 = "",
            resultado = "",
            onNumero1Change = {},
            onNumero2Change = {},
            onCalcular = {},
            onLimpiar = {}
        )
    }
}

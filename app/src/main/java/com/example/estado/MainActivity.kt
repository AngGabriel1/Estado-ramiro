
package com.example.estado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.estado.Screens.InicioScreen
import com.example.estado.ui.theme.EstadoTheme

data class Persona(
    val nombre: String,
    val color: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EstadoTheme {

                var nombre by remember { mutableStateOf("") }
                var color by remember { mutableStateOf("") }

                val listaPersonas = remember {
                    mutableStateListOf<Persona>()
                }

                InicioScreen(
                    modifier = Modifier.fillMaxSize(),
                    nombre = nombre,
                    color = color,
                    listaPersonas = listaPersonas,
                    onNombreChange = {
                        nombre = it
                    },
                    onColorChange = {
                        color = it
                    },
                    onAgregar = {
                        listaPersonas.add(
                            Persona(
                                nombre = nombre,
                                color = color
                            )
                        )

                        nombre = ""
                        color = ""
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    ) //ola
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EstadoTheme {
        InicioScreen(
            modifier = Modifier.fillMaxSize(),
            nombre = "",
            color = "",
            listaPersonas = emptyList(),
            onNombreChange = {},
            onColorChange = {},
            onAgregar = {}
        )
    }
}

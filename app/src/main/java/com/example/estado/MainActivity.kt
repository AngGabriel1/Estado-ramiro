
package com.example.estado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.estado.ui.theme.EstadoTheme
import screens.InicioScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EstadoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    var color by rememberSaveable {
                        mutableStateOf("")
                    }

                    var colorSeleccionado by rememberSaveable {
                        mutableStateOf("azul")
                    }

                    val listaNombres = remember {
                        mutableStateListOf(
                            "Gabriel",
                            "Juan",
                            "María",
                            "Pedro"
                        )
                    }

                    InicioScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),

                        color = color,

                        colorSeleccionado = colorSeleccionado,

                        listaNombres = listaNombres,

                        onColorChange = {
                            color = it
                        },

                        onCambiarColor = {
                            colorSeleccionado = color
                        }
                    )
                }
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
            color = "",
            colorSeleccionado = "azul",
            listaNombres = emptyList(),
            onColorChange = {},
            onCambiarColor = {}
        )
    }
}

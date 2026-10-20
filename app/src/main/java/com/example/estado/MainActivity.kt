
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    var nombre by rememberSaveable {
                        mutableStateOf("")
                    }

                    val listaNombres = remember {
                        mutableStateListOf<String>()
                    }

                    InicioScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        nombre = nombre,
                        listaNombres = listaNombres,
                        onNombreChange = { nombre = it },
                        onAgregar = {
                            listaNombres.add(nombre)
                            nombre = ""
                        },
                        onEliminar = {
                            listaNombres.remove(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EstadoTheme {
        InicioScreen(
            modifier = Modifier.fillMaxSize(),
            nombre = "",
            listaNombres = emptyList(),
            onNombreChange = {},
            onAgregar = {},
            onEliminar = {}
        )
    }
}


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
                    var numero1 by remember {
                        mutableStateOf("")
                    }

                    var numero2 by remember {
                        mutableStateOf("")
                    }

                    var resultado by remember {
                        mutableStateOf("")
                    }


                    InicioScreen(
                        modifier = Modifier
                            .fillMaxSize(),

                        numero1 = numero1,

                        numero2 = numero2,

                        resultado= resultado,

                        onCambiarNumero1 = { numero1 = it },

                        onCambiarNumero2 = { numero2 = it},

                        onCambiarResultado = {}

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
            numero1 = "",
            numero2= "",
            resultado = "",
            onCambiarNumero1 = {},
            onCambiarNumero2 = {},
            onCambiarResultado = {}

        )
    }
}

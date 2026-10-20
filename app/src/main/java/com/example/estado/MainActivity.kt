
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

                    var numero1 by rememberSaveable {
                        mutableStateOf("")
                    }

                    var numero2 by rememberSaveable {
                        mutableStateOf("")
                    }

                    var resultado by rememberSaveable {
                        mutableStateOf("")
                    }

                    InicioScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        numero1 = numero1,
                        numero2 = numero2,
                        resultado = resultado,
                        onNumero1Change = { numero1 = it },
                        onNumero2Change = { numero2 = it },
                        onSumar = {
                            resultado = (
                                    numero1.toDouble() +
                                            numero2.toDouble()
                                    ).toString()
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
            numero1 = "",
            numero2 = "",
            resultado = "",
            onNumero1Change = {},
            onNumero2Change = {},
            onSumar = {}
        )
    }
}

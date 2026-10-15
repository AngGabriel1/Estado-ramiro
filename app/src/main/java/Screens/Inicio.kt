package Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text


@Composable
fun Inicio() {
    var nombre by rememberSaveable {
        mutableStateOf("")
    }

    val listaNombres = remember {
        mutableListOf<String>()
    }

    Column( modifier = Modifier.fillMaxSize().padding(15.dp)) {
        TextField(
            value = nombre,
            onValueChange = {
                nombre = it
            },
            modifer = Modifier.fillMaxWidth(),
            label = {
                Text("Escribe un Nombre")
            }

        )

    }
}
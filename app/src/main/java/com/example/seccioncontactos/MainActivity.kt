package com.example.contactlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactosLista(
                        contactos = contactos(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
        }
    }
}

data class Contactos(val name: String)

fun contactos(): Map<Char, List<String>> {
    val lista = listOf(
        "Alicia", "Adrian", "Barbara", "Belen", "Carla", "Carmen",
        "David", "Diana", "Eduardo", "Esteban", "Fran", "Fernando", "Gema", "Juan",
        "Paco", "Pepe", "Alvaro", "Angel", "Luis", "Jose", "Curro", "Jesus", "Laura"
    ).sorted()

    return lista.groupBy { it.first() }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactosLista(contactos: Map<Char, List<String>>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        contactos.forEach { (inicial, listaContactos) ->
            stickyHeader {
                HeaderItem(inicial)
            }
            items(listaContactos) { contacto ->
                ContactItem(contacto)
            }
        }
    }
}

@Composable
fun HeaderItem(inicial: Char) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Text(
            text = inicial.toString(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun ContactItem(contacto: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(
            text = contacto,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContactList() {

}

package com.example.itinerarioactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itinerarioactivity.data.Datasource
import com.example.itinerarioactivity.model.Task
import com.example.itinerarioactivity.ui.theme.ItinerarioActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItinerarioActivityTheme {
                TasksApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TasksApp(){
    // Leemos la db y la cargamos en un elemento cuyo estado puede cambiar
    var taskList by remember { mutableStateOf(Datasource().readDB()) }

    // Definimos la estructura de la app
    Scaffold (
        topBar = { Topbar() },
        floatingActionButton = { CreateButton() },
    ){it ->
        LazyColumn(contentPadding = it){
            items(taskList){
                val haptics = LocalHapticFeedback.current
                var taskId = ""

                // Hacemos que cada elemento despliegue una alerta tras un click largo (no está funcionando :( )
                TaskCard(
                    task = it,
                    modifier = Modifier
                        .combinedClickable (
                            onClick = {taskId = it.id},
                            onLongClick = {
                                // Damos feedback al usuario del click largo
                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            },
                            onLongClickLabel = stringResource(R.string.del_elem)
                        )
                )
            }
        }
    }
}

@Composable
fun CreateButton() {
    // Botón para crear una nueva tarea
    IconButton(
        modifier = Modifier
            .size(100.dp),
        onClick = {
            // Navegar a la vista de crear tarea
            //...
        }
    ) {
        Icon(
            imageVector = Icons.Default.AddCircle,
            contentDescription = "Crear tarea",
            tint = Color.Blue,
            modifier = Modifier
                .size(300.dp)
        )
    }
}

@Preview
@Composable
fun GreetingPreview() {
    ItinerarioActivityTheme {
        TasksApp()
    }
}
package com.example.itinerarioactivity

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itinerarioactivity.data.Datasource
import com.example.itinerarioactivity.model.Task
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID



@Composable
fun TaskCard(task: Task, modifier: Modifier = Modifier){
    Card (modifier = Modifier.padding(vertical = 8.dp)) {
        var showDialog by rememberSaveable { mutableStateOf(false) }

        // Mostramos alerta si el usuario presiona el boton de borrar elemento
        if(showDialog){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            // Eliminamos el recurso
                            Datasource().deleteFromDB(task.id)
                            showDialog = false

                            // Recargar la vista para mostrar los cambios
                            //...
                        }) {
                        Text(text = "Eliminar")
                    }
                },

                title = {
                    Text(text = stringResource(R.string.del_elem_Title))
                },
                text = {
                    Text(text = stringResource(R.string.del_elem))
                },
                
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Cancelar")
                    }
                }
            )
        }

        Column (
            modifier = Modifier
                .padding(10.dp)
                .padding(vertical = 5.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row {
                    Text(
                        text = "Area: ",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = task.area)
                }
                // Obtener el contexto actual
                val context = LocalContext.current

                // Icono de teléfono clicable
                // Al presionar debe abrir el dialog de Android (No funciona :( )
                IconButton(
                    onClick = {
                        // Iniciar el Dial

                        // Crear la intención para abrir el marcador
                        val dialIntent = Intent(Intent.ACTION_DIAL)

                        // Verificar si hay una aplicación de marcación disponible
                        if (dialIntent.resolveActivity(context.packageManager) != null) {
                            // Iniciar la actividad del marcador
                            context.startActivity(dialIntent)
                        } else {
                            // Mostrar un error al usuario
                            // ...
                        }


                    }
                ) {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Llamar",
                        modifier = Modifier
                            .size(50.dp))
                }
            }
            Row (
                modifier = Modifier
                    .padding(5.dp)
            ){
                Text(text = "Fecha: ", fontWeight = FontWeight.Bold)
                Text(text = task.date.toString(),
                    modifier = Modifier
                        .padding(end = 5.dp))
                Text(text = "Inicio: ", fontWeight = FontWeight.Bold)
                Text(text = task.startTime.toString(),
                    modifier = Modifier
                        .padding(end = 5.dp))
                Text(text = "Fin: ", fontWeight = FontWeight.Bold)
                Text(text = task.endTime.toString())
            }
            Row {
                Text(text = "Solicitado por: ", fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 5.dp))
                Text(text = task.booker)
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Text(text = "Actividad a realizar: ", fontWeight = FontWeight.Bold)
                    Text(text = task.activity)
                }

                // Icono de borrar elemento
                IconButton(
                    onClick = {
                        // Desplegar ventana con la pregunta de borrar elemento
                        showDialog = true;
                    }
                ) {
                    Icon(imageVector = Icons.Default.Clear,
                        contentDescription = "Borrar tarea",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(50.dp))
                }
            }

        }
    }
}

@Composable
@Preview
fun CardPreview()
{
    val testTask = Task(
        id = UUID.randomUUID().toString(),
        area = "AreaB",
        date = LocalDate.of(2024, 2, 15),
        startTime = LocalTime.of(14, 30),
        endTime = LocalTime.of(16, 0),
        booker = "User2",
        activity = "Presentation"
    )
    
    TaskCard(task = testTask)
}
package com.example.itinerarioactivity

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itinerarioactivity.model.Task
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID



@Composable
fun TasksList(tasksList: List<Task>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier){
        //Por cada elemento en la lista imprimimos una card
        items(tasksList) { task ->
            TaskCard(task = task)
        }

    }
}

@Composable
fun TaskCard(task: Task,
             modifier: Modifier = Modifier
){
    Card (modifier = Modifier.padding(vertical = 8.dp)) {
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

                // Icono de teléfono clicable
                IconButton(
                    onClick = {
                        // Iniciar el Dial
                        /*
                        // Obtener el contexto actual
                        val context = LocalContext.current

                        // Crear la intención para abrir el marcador
                        val dialIntent = Intent(Intent.ACTION_DIAL)

                        // Verificar si hay una aplicación de marcación disponible
                        if (dialIntent.resolveActivity(context.packageManager) != null) {
                            // Iniciar la actividad del marcador
                            context.startActivity(dialIntent)
                        } else {

                        }
                        */

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
            Text(text = "Actividad a realizar: ", fontWeight = FontWeight.Bold)
            Text(text = task.activity)
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
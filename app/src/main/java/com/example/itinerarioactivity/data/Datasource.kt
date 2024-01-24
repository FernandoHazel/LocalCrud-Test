package com.example.itinerarioactivity.data

import android.os.Build
import com.example.itinerarioactivity.model.Task
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import java.io.BufferedReader
import java.io.InputStreamReader


class Datasource() {

    // Leer el archivo json
    fun readJsonFile(fileName: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()

        reader.use {
            var line = it.readLine()
            while (line != null) {
                stringBuilder.append(line)
                line = it.readLine()
            }
        }

        return stringBuilder.toString()
    }

    // Guardamos los datos en una lista para trabajar con ellos
    // Más adelante los guardaré de manera local con un json o en SQLite si me da tiempo
    // Hubo que requerir esta API para que el método of() de LocalDate y LocalTime pudiera funcionar
    var tasks = mutableListOf<Task>(
        Task(
            id = UUID.randomUUID().toString(),
            area = "AreaA",
            date = LocalDate.of(2024, 1, 24),
            startTime = LocalTime.of(9, 0),
            endTime = LocalTime.of(11, 0),
            booker = "User1",
            activity = "Meeting"
        ),
        Task(
            id = UUID.randomUUID().toString(),
            area = "AreaB",
            date = LocalDate.of(2024, 2, 15),
            startTime = LocalTime.of(14, 30),
            endTime = LocalTime.of(16, 0),
            booker = "User2",
            activity = "Presentation"
        ),
        Task(
            id = UUID.randomUUID().toString(),
            area = "AreaC",
            date = LocalDate.of(2024, 3, 5),
            startTime = LocalTime.of(10, 0),
            endTime = LocalTime.of(12, 0),
            booker = "User3",
            activity = "Training"
        ),
        Task(
            id = UUID.randomUUID().toString(),
            area = "AreaA",
            date = LocalDate.of(2024, 4, 10),
            startTime = LocalTime.of(13, 0),
            endTime = LocalTime.of(15, 0),
            booker = "User1",
            activity = "Meeting"
        ),
        Task(
            id = UUID.randomUUID().toString(),
            area = "AreaB",
            date = LocalDate.of(2024, 5, 20),
            startTime = LocalTime.of(11, 0),
            endTime = LocalTime.of(13, 0),
            booker = "User2",
            activity = "Presentation"
        )
    )

    // Método para leer la db
    fun readDB(): List<Task>{
        return tasks
    }

    // Método para eliminar dato de la db
    fun deleteFromDB(id:String){
        tasks.removeIf{ it.id == id }
    }
}
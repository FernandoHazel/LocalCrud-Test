package com.example.itinerarioactivity.model

import java.time.LocalDate
import java.time.LocalTime

// Modelamos la tabla
data class Task(
    val id: String,
    val area: String,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val booker: String,
    val activity: String,
)

package com.example.trips

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun today(): String {
    val agora = LocalDateTime.now()
    val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return agora.format(formato)
}
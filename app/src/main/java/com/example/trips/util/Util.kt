package com.example.trips

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun today(): String {
    val now = LocalDateTime.now()
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return now.format(format)
}

fun convertLongToString(date: Long): String {
    val dt = Instant.ofEpochSecond(date)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
    val defaultExpected =
        "([A-Za-z0-9]{4})-([A-Za-z0-9]{2})-([A-Za-z0-9]{2})T([A-Za-z0-9]{2}):([A-Za-z0-9]{2})".toRegex()
    val formatExpected = "\$3/\$2/\$1 \$4:\$5"
    return "${dt.toString().replace(defaultExpected, formatExpected)}"
}

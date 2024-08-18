package com.example.harrypotter.util

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(dateString: String): String {
    val inputSdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val outputSdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val date = inputSdf.parse(dateString)
    return outputSdf.format(date)
}
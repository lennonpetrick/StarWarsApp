package com.test.starwarsapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun stringToDate(string: String): Date {
    return simpleDateFormat("yyyy-MM-dd").parse(string)
}

fun dateToString(date: Date): String {
    return simpleDateFormat("yyyy-MM-dd").format(date)
}

fun displayDate(date: Date): String {
    return simpleDateFormat("dd/MM/yyyy").format(date)
}

private fun simpleDateFormat(pattern: String): SimpleDateFormat {
    return SimpleDateFormat(pattern, Locale.getDefault())
}
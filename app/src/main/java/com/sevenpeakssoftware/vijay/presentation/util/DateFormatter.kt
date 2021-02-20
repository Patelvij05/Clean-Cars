package com.sevenpeakssoftware.vijay.presentation.util

import android.content.Context
import android.text.format.DateFormat
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import java.util.*

private val DATE_FORMATTER_CURRENT_YEAR_24H = DateTimeFormatter.ofPattern("dd MMMM, HH:mm")
private val DATE_FORMATTER_CURRENT_YEAR_12H = DateTimeFormatter.ofPattern("dd MMMM, hh:mm a")
private val DATE_FORMATTER_DIFFERENT_YEAR = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm")
private val DATE_FORMATTER_DIFFERENT_YEAR_12H = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a")

fun LocalDateTime.toWithinCurrentYear(appContext: Context): String {
    return if(DateFormat.is24HourFormat(appContext)) {
        this.format(DATE_FORMATTER_CURRENT_YEAR_24H)
    } else {
        this.format(DATE_FORMATTER_CURRENT_YEAR_12H)
    }
}

fun LocalDateTime.toWithinDifferentYear(appContext: Context): String {
    return if(DateFormat.is24HourFormat(appContext)) {
        this.format(DATE_FORMATTER_DIFFERENT_YEAR)
    } else {
        this.format(DATE_FORMATTER_DIFFERENT_YEAR_12H)
    }
}

fun LocalDateTime.isCurrentYear(): Boolean {
    return this.year == Calendar.getInstance().get(Calendar.YEAR)
}
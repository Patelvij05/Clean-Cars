package com.sevenpeakssoftware.vijay.domain.model.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class ZonedDateTimeAdapter {

    @FromJson
    fun fromJson(json: String?): LocalDateTime? {
        if(json.isNullOrEmpty()){
            return null
        }

        return LocalDateTime.parse(json, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
    }

    @ToJson
    fun toJson(value: ZonedDateTime?): String? {
        return value?.toString()
    }

}
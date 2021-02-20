package com.sevenpeakssoftware.vijay.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDateTime

@JsonClass(generateAdapter = true)
data class Articles(val content: List<Article>)

@JsonClass(generateAdapter = true)
data class Article(
    val id: Long?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "ingress")
    val ingress: String?,
    @Json(name = "image")
    val image: String?,
    val dateTime: LocalDateTime?,
    val content: List<Item?>
)

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "type")
    val type: String?,
    @Json(name = "subject")
    val subject: String?,
    @Json(name = "description")
    val description: String?
)
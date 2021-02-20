package com.sevenpeakssoftware.vijay.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_articles")
data class CarsArticle(
    @PrimaryKey var id: Long,
    var title: String,
    val datetime: String,
    val image: String?,
    val ingress: String
)
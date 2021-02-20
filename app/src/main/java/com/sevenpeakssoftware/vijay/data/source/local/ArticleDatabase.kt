package com.sevenpeakssoftware.vijay.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sevenpeakssoftware.vijay.data.source.local.dao.ArticleDao
import com.sevenpeakssoftware.vijay.data.source.local.entity.CarsArticle

/**
 * To manage data items that can be accessed, updated
 * & maintain relationships between them
 */
@Database(entities = [CarsArticle::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}
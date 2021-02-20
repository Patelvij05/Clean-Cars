package com.sevenpeakssoftware.vijay.data.source.local.dao

import androidx.room.*
import com.sevenpeakssoftware.vijay.data.source.local.entity.CarsArticle
import io.reactivex.rxjava3.core.Flowable

/**
 * it provides access to [CarsArticle] underlying database
 * */
@Dao
interface ArticleDao {

    @Query("SELECT * FROM car_articles")
    fun getCarArticles(): Flowable<List<CarsArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<CarsArticle>)

    @Update
    fun updateArticle(article: CarsArticle)
}
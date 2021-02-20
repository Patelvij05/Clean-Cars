package com.sevenpeakssoftware.vijay.di

import android.app.Application
import androidx.room.Room
import com.sevenpeakssoftware.vijay.data.source.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(app, ArticleDatabase::class.java, "article_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideArticleDao(db: ArticleDatabase) = db.articleDao()

}
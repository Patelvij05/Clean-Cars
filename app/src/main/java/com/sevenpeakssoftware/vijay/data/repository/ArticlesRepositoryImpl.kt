package com.sevenpeakssoftware.vijay.data.repository

import com.sevenpeakssoftware.vijay.data.source.local.ArticleDatabase
import com.sevenpeakssoftware.vijay.data.source.local.entity.CarsArticle
import com.sevenpeakssoftware.vijay.data.source.remote.ArticleService
import com.sevenpeakssoftware.vijay.domain.model.Articles
import com.sevenpeakssoftware.vijay.domain.repository.ArticleRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * This repository is responsible for
 * fetching data[Articles] from server or db
 * */
class ArticlesRepositoryImpl(
    private val db: ArticleDatabase,
    private val articleService: ArticleService
): ArticleRepository{

    override fun getArticles(): Single<Articles> {
        return articleService.getArticlesList()
    }

    override fun addArticle(article: List<CarsArticle>) {
        db.articleDao().insertArticles(article)
    }

    override fun updateArticle(article: CarsArticle) {
        db.articleDao().updateArticle(article)
    }

    override fun loadAll(): Flowable<List<CarsArticle>> {
        return db.articleDao().getCarArticles()
    }

}
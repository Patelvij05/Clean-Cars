package com.sevenpeakssoftware.vijay.domain.interactors

import android.content.Context
import com.sevenpeakssoftware.vijay.data.source.local.entity.CarsArticle
import com.sevenpeakssoftware.vijay.domain.model.Articles
import com.sevenpeakssoftware.vijay.domain.repository.ArticleRepository
import com.sevenpeakssoftware.vijay.presentation.util.*
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

/**
 * An interactor that calls the actual implementation of [ArticleViewModel]
 * (which is injected by DI) it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetArticleInteractor @Inject constructor(
    @ApplicationContext appContext: Context,
    private val repository: ArticleRepository
) {

    private val currentArticle =
        object : NetworkBoundResource<List<CarsArticle>, Articles>() {
            override fun saveCallResult(item: Articles) {
                val response = item.content.map {
                    CarsArticle(
                        id = it.id!!,
                        title = it.title!!,
                        datetime = if (it.dateTime!!.isCurrentYear())
                            it.dateTime!!.toWithinCurrentYear(appContext)
                        else it.dateTime!!.toWithinDifferentYear(appContext),
                        image = it.image,
                        ingress = it.ingress!!
                    )
                }
                repository.addArticle(response)
            }

            override fun shouldFetch(): Boolean = true

            override fun loadFromDb(): Flowable<List<CarsArticle>> {
                return repository.loadAll()
            }

            override fun createCall(): Observable<Resource<Articles>> =
                repository.getArticles().flatMapObservable { articles ->
                    Observable.just(Resource.success(articles))
                }

        }

    fun getCarArticlesObservable(): Observable<Resource<List<CarsArticle>>> {
        return currentArticle.getAsObservable()
    }

}
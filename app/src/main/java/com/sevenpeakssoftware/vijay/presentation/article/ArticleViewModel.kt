package com.sevenpeakssoftware.vijay.presentation.article

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sevenpeakssoftware.vijay.data.source.local.entity.CarsArticle
import com.sevenpeakssoftware.vijay.domain.interactors.GetArticleInteractor
import com.sevenpeakssoftware.vijay.presentation.util.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

/**To store & manage UI-related data in a lifecycle conscious way!
 * this class allows data to survive configuration changes such as screen rotation
 * by interacting with [GetArticleInteractor]
 *
 * */
class ArticleViewModel @ViewModelInject constructor(
    private val articleInteractor: GetArticleInteractor
) : ViewModel() {

    private val TAG = ArticleViewModel::class.java.simpleName
    private val compositeDisposable = CompositeDisposable()
    private val carArticleLiveData = MutableLiveData<Resource<List<CarsArticle>>>()

    val article: LiveData<Resource<List<CarsArticle>>>
        get() = carArticleLiveData

    init {
        getCarArticle()
    }

    fun onManualRefresh() {
        getCarArticle()
    }

    override fun onCleared() {
        // To avoid memory leak
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun getCarArticle() {
        compositeDisposable.add(
            articleInteractor.getCarArticlesObservable()
                .doOnSubscribe {
                    carArticleLiveData.value = Resource.loading(null)
                    compositeDisposable.add(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    carArticleLiveData.value = Resource.success(it.data!!)
                }, { t ->
                    Timber.e(t)
                    carArticleLiveData.value = Resource.error(t.message!!, null)
                })
        )
    }

}
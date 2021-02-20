package com.sevenpeakssoftware.vijay.presentation.util

import com.sevenpeakssoftware.vijay.data.source.local.entity.CarsArticle

object TestUtil {

    fun createArticle() = CarsArticle(
        id = 0L,
        title = "",
        datetime = "",
        ingress = "",
        image = ""
    )

    fun createCarsArticleList(size: Int): List<CarsArticle> {
        val list = ArrayList<CarsArticle>(size)
        list.forEach {
            it.title = "CarsArticle ${list.indexOf(it)}"
            it.id = (list.indexOf(it) + 1).toLong()
            list.add(it)
        }
        return list
    }

}
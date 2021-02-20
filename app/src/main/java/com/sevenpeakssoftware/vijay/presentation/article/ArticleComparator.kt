package com.sevenpeakssoftware.vijay.presentation.article

import androidx.recyclerview.widget.DiffUtil
import com.sevenpeakssoftware.vijay.data.source.local.entity.CarsArticle
import com.sevenpeakssoftware.vijay.domain.model.Article

class ArticleComparator: DiffUtil.ItemCallback<CarsArticle>() {
    override fun areItemsTheSame(oldItem: CarsArticle, newItem: CarsArticle) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CarsArticle, newItem: CarsArticle) =
        oldItem == newItem
}
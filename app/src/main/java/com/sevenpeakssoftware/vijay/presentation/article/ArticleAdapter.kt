package com.sevenpeakssoftware.vijay.presentation.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sevenpeakssoftware.vijay.R
import com.sevenpeakssoftware.vijay.data.source.local.entity.CarsArticle
import com.sevenpeakssoftware.vijay.databinding.ItemArticleBinding

/**
 * This class is responsible for converting each data entry [CarsArticle]
 * into [ArticleViewHolder] that can then be added to the AdapterView.
 */
class ArticleAdapter: ListAdapter<CarsArticle, ArticleAdapter.ArticleViewHolder>(ArticleComparator()) {

    /**
     * This method is called right when adapter is created &
     * is used to initialize ViewHolders
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    /** It is called for each ViewHolder to bind it to the adapter &
     * This is where we pass data to ViewHolder
     * */
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it) }
    }

    class ArticleViewHolder(
        private val binding: ItemArticleBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(article: CarsArticle) {
            binding.apply {
                Glide.with(itemView)
                    .load(article.image)
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_placeholder)
                    .into(imageView)

                textViewTitle.text = article.title
                textViewDateTime.text = article.datetime
                textViewDescription.text = article.ingress
            }
        }

    }

}
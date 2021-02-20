package com.sevenpeakssoftware.vijay.presentation.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sevenpeakssoftware.vijay.R
import com.sevenpeakssoftware.vijay.databinding.FragmentArticleBinding
import com.sevenpeakssoftware.vijay.presentation.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loading.*
import timber.log.Timber

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var articleAdapter: ArticleAdapter
    private val articleViewModel: ArticleViewModel by viewModels()
    private val binding by viewBinding(FragmentArticleBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleAdapter = ArticleAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = articleAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            swipeRefreshLayout.setOnRefreshListener {
                articleViewModel.onManualRefresh()
            }

            button_retry.setOnClickListener{
                articleViewModel.onManualRefresh()
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        articleViewModel.article.observe(viewLifecycleOwner, Observer {

            if(it.isLoading) {
                showLoading(true)
                handleErrorResponse(false)
            } else if(it.data!!.isNotEmpty()) {
                showLoading(false)
                handleErrorResponse(false)
                articleAdapter.submitList(it.data)
            } else {
                Timber.e("error check this %s", it.message)
                showLoading(false)
                handleErrorResponse(true)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        articleViewModel.onManualRefresh()
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow){
            binding.apply {
                loading_container.visibility = View.VISIBLE
                swipeRefreshLayout.isRefreshing = isShow
            }
        } else {
            binding.apply {
                loading_container.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = isShow
            }
        }
    }

    private fun handleErrorResponse(isError: Boolean) {
        if(isError) {
            binding.apply {
                layoutError.buttonRetry.visibility = View.VISIBLE
                layoutError.textViewError.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            Snackbar.make(requireView(), "An error occurred!", Snackbar.LENGTH_SHORT).show()
        } else {
            binding.apply {
                layoutError.buttonRetry.visibility = View.GONE
                layoutError.textViewError.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }

}
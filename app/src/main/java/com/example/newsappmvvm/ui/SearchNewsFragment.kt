package com.example.newsappmvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappmvvm.MainActivity
import com.example.newsappmvvm.R
import com.example.newsappmvvm.adapters.NewsAdapter
import com.example.newsappmvvm.databinding.FragmentSearchNewsBinding
import com.example.newsappmvvm.ui.viewmodels.NewsViewModel
import com.example.newsappmvvm.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment:Fragment(R.layout.fragment_search_news) {
    lateinit var viewModel: NewsViewModel
    private var binding:FragmentSearchNewsBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchNewsBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val newsAdapter:NewsAdapter = NewsAdapter()
        binding!!.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        viewModel.searchNews.observe(viewLifecycleOwner, Observer { it ->
            when(it){
                is Resource.Success -> {binding!!.paginationProgressBar.visibility = View.INVISIBLE
                    it.data?.let { newsAdapter.differ.submitList(it.articles) }}
                is Resource.Error -> binding!!.paginationProgressBar.visibility = View.INVISIBLE
                is Resource.Loading ->binding!!.paginationProgressBar.visibility = View.VISIBLE
            }
        })
        var job:Job? = null
        binding!!.etSearch.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(2000L)
                it?.let {
                    if (it.toString().isNotEmpty()){
                        viewModel.searchNews(it.toString().trim())
                    }
                }
            }
        }

    }



}
package com.example.newsappmvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappmvvm.MainActivity
import com.example.newsappmvvm.NewsApplication
import com.example.newsappmvvm.R
import com.example.newsappmvvm.adapters.NewsAdapter
import com.example.newsappmvvm.databinding.FragmentBreakingNewsBinding
import com.example.newsappmvvm.repository.NewsRepository
import com.example.newsappmvvm.ui.viewmodels.NewsViewModel
import com.example.newsappmvvm.ui.viewmodels.NewsViewModelProviderFactory
import com.example.newsappmvvm.util.Resource

class BreakingNewsFragment:Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewModel: NewsViewModel

    private var  binding:FragmentBreakingNewsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreakingNewsBinding.inflate(inflater,container,false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val newsAdapter = NewsAdapter()
        binding!!.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {binding!!.paginationProgressBar.visibility = View.INVISIBLE
                it.data?.let { newsAdapter.differ.submitList(it.articles) }}
                is Resource.Error -> binding!!.paginationProgressBar.visibility = View.INVISIBLE
                is Resource.Loading ->binding!!.paginationProgressBar.visibility = View.VISIBLE
            }
        })


    }
}
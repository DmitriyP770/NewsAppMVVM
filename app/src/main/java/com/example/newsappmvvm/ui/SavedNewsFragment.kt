package com.example.newsappmvvm.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newsappmvvm.R
import com.example.newsappmvvm.ui.viewmodels.NewsViewModel

class SavedNewsFragment:Fragment(R.layout.fragment_saved_news) {
    private val viewModel: NewsViewModel by activityViewModels()

}
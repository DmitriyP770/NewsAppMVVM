package com.example.newsappmvvm.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvm.api.NewsApi
import com.example.newsappmvvm.models.NewsResponseApi
import com.example.newsappmvvm.repository.NewsRepository
import com.example.newsappmvvm.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel (val repository: NewsRepository): ViewModel() {
    private var pageNum: Int = 1
     var breakingNews:MutableLiveData<Resource<NewsResponseApi>> = MutableLiveData()

    private var searchPage:Int = 1
     var searchNews:MutableLiveData<Resource<NewsResponseApi>> = MutableLiveData()

    init {
        getBreakingNews("us")
    }


    private fun getBreakingNews(countryCode:String) =
        viewModelScope.launch {
            breakingNews.postValue(Resource.Loading())
            val response =  repository.getNewsFromApi(countryCode,pageNum)
            breakingNews.postValue(handleResponse(response))
        }

    fun searchNews(query:String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        var response = repository.searchNews(query,searchPage)
        searchNews.postValue(handleSearchNewsResponse(response))

    }

    private fun handleSearchNewsResponse(response: Response<NewsResponseApi>):
            Resource<NewsResponseApi> {
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }


    private fun handleResponse(response: Response<NewsResponseApi>): Resource<NewsResponseApi>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())

    }


}
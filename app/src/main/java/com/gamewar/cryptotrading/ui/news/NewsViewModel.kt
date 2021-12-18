package com.gamewar.cryptotrading.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamewar.cryptoapi.models.entities.NewsData
import com.gamewar.cryptotrading.data.CryptoCompareRepository
import com.gamewar.cryptotrading.utils.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    private val repo = CryptoCompareRepository()

    //live data for news response
    val news: MutableLiveData<ApiResponse<List<NewsData>>> = MutableLiveData()

    fun getNews() = viewModelScope.launch(Dispatchers.IO) {
        news.postValue(ApiResponse.Loading())

        val response = repo.getLatestNews()
        if (response != null) {
            news.postValue(ApiResponse.Success(response))
        }else {
            news.postValue(ApiResponse.Error("Could not retrieve news, try again!"))
        }
    }
}
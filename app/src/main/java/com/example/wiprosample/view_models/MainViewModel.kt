package com.example.wiprosample.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wiprosample.data_models.Feed
import com.example.wiprosample.data_models.FeedResponse
import com.example.wiprosample.repository.FeedRepo
import com.example.wiprosample.retrofit.ResponseWrapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel:ViewModel(),KoinComponent {


    private val feedRepo by inject<FeedRepo>()

    private val _feedData = MutableLiveData<ResponseWrapper<FeedResponse>>()
    val feedList: LiveData<ResponseWrapper<FeedResponse>> = _feedData

    fun fetchFeed(forceRefresh:Boolean) = viewModelScope.launch {
        if(forceRefresh){
            feeds()
        }
        _feedData.value?:feeds()
    }

    private suspend fun feeds(){
        feedRepo.fetchFeeds()
            .collect {
            _feedData.value = it
        }
    }
}
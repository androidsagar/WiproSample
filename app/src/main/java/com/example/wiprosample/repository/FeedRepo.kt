package com.example.wiprosample.repository

import android.app.Application
import com.example.wiprosample.R
import com.example.wiprosample.retrofit.FeedRequest
import com.example.wiprosample.retrofit.ResponseWrapper
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class FeedRepo:KoinComponent {

    private val feedRequest:FeedRequest by inject()
    private val context:Application by inject()

    fun fetchFeeds() = flow {
        emit(ResponseWrapper.Loading)
        try {
            val response = feedRequest.fetchFeedFromServer()
            emit(ResponseWrapper.Success(response.feedList))
        }catch (e:Exception){
          e.printStackTrace()
          emit(ResponseWrapper.Error(e.message?:context.getString(R.string.something_went_wrong)))
        }
    }
}
package com.example.wiprosample.repository

import android.app.Application
import com.example.wiprosample.R
import com.example.wiprosample.data_models.FeedResponse
import com.example.wiprosample.retrofit.FeedRequest
import com.example.wiprosample.retrofit.ResponseWrapper
import kotlinx.coroutines.coroutineScope
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
            filterNull(response)
            emit(ResponseWrapper.Success(response))
        }catch (e:Exception){
          e.printStackTrace()
          emit(ResponseWrapper.Error(e.message?:context.getString(R.string.something_went_wrong)))
        }
    }

    private suspend fun filterNull(response: FeedResponse) = coroutineScope {
        response.feedList = response.feedList.filter {
            (!it.title.isNullOrBlank() && !it.title.isNullOrBlank() && !it.title.isNullOrBlank())
        }.map {
            it.apply {
                title = title?:context.getString(R.string.title_not_available)
                description = description?:context.getString(R.string.description_not_available)
            }
        }
    }
}
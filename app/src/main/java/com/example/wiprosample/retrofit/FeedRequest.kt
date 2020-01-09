package com.example.wiprosample.retrofit

import com.example.wiprosample.data_models.FeedResponse
import retrofit2.http.POST

interface FeedRequest {

    @POST("facts.json")
    suspend fun fetchFeedFromServer(): FeedResponse
}
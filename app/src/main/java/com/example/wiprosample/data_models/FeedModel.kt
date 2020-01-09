package com.example.wiprosample.data_models

import com.google.gson.annotations.SerializedName


data class FeedResponse(
    val title: String,
    @SerializedName("rows") val feedList: MutableList<Feed>
)

data class Feed(
    val title: String?,
    val description: String?,
    @SerializedName("imageHref") val imageUrl: String?
)
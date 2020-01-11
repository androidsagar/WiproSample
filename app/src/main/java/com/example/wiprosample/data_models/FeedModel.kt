package com.example.wiprosample.data_models

import com.google.gson.annotations.SerializedName


data class FeedResponse(
    val title: String,
    @SerializedName("rows") var feedList: List<Feed>
)

data class Feed(
    var title: String?,
    var description: String?,
    @SerializedName("imageHref") val imageUrl: String?
)
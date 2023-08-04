package com.example.videogazing.api.model

data class GetVideosResponse(
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
)
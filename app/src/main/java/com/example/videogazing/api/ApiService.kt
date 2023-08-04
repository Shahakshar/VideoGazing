package com.example.videogazing.api

import com.example.videogazing.api.model.GetVideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface ApiService {

    @GET("/search")
    suspend fun GetVideos(
        @QueryMap queries: HashMap<String, String>,
        @Header("X-RapidAPI-Key")  apikey: String = Constent.API_KEY,
        @Header("X-RapidAPI-Host") host: String = Constent.HOST
        ): Response<GetVideosResponse>

}
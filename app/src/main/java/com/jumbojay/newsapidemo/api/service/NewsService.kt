package com.jumbojay.newsapidemo.api.service

import com.jumbojay.newsapidemo.data.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
    ): ApiResult
}
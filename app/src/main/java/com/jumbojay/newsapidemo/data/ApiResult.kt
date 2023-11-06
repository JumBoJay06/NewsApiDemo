package com.jumbojay.newsapidemo.data

import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class ApiResult(
    @Expose
    val status: String = "",
    @Expose
    val totalResults: Int = 0,
    @Expose
    val articles: List<News> = emptyList()
)

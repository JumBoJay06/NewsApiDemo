package com.jumbojay.newsapidemo.di

import com.jumbojay.newsapidemo.api.buildWithApi
import com.jumbojay.newsapidemo.api.service.NewsService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { getWebService<NewsService>(10L) }
}
inline  fun <reified T> getWebService(
    readTimeout: Long
): T = Retrofit.Builder()
    .buildWithApi(readTimeout)

val myModules = listOf(
    apiModule,
)
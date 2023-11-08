package com.jumbojay.newsapidemo.ui

import com.jumbojay.newsapidemo.api.service.NewsService
import com.jumbojay.newsapidemo.data.toDbObject
import com.jumbojay.newsapidemo.db.NewsDaoImpl
import timber.log.Timber

class NewsRepository(
    private val newsService: NewsService,
    private val newsDaoImpl: NewsDaoImpl
) {
    fun newsFlow() = newsDaoImpl.findAll()

    suspend fun updateNews(country: String, apiKey: String) {
        Timber.tag("call api").i("getHeadlines")
        val news = newsService.getHeadlines(country, apiKey)
        if (news.status == "ok" && news.totalResults > 0) {
            val newsObjects = news.articles.map {
                it.toDbObject()
            }
            Timber.tag("call api").i("map to db")
            newsDaoImpl.add(newsObjects)
            Timber.tag("call api").i("save db done")
        }
    }
}
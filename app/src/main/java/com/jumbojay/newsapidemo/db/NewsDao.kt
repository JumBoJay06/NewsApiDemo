package com.jumbojay.newsapidemo.db

import com.jumbojay.newsapidemo.data.News
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface NewsDao {
    suspend fun add(news: List<NewsObject>): Boolean
    fun findAll(): Flow<ResultsChange<NewsObject>>?
    suspend fun deleteAll(): Boolean
}
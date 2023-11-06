package com.jumbojay.newsapidemo.data

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class News(
    val source: Source = Source(),
    val author: String = "",
    @PrimaryKey val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
) : RealmObject

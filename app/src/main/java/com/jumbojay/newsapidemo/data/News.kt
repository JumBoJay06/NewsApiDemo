package com.jumbojay.newsapidemo.data

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.jumbojay.newsapidemo.db.NewsObject
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

@Keep
data class News(
    @Expose
    var source: NewsSource? = NewsSource(),
    @Expose
    var author: String = "",
    @Expose
    var title: String = "0",
    @Expose
    var description: String = "",
    @Expose
    var url: String = "",
    @Expose
    var urlToImage: String = "",
    @Expose
    var publishedAt: String = "",
    @Expose
    var content: String = ""
) {
    override fun toString(): String {
        return "News(source=$source, author='$author', title='$title', description='$description', url='$url', urlToImage='$urlToImage', publishedAt='$publishedAt', content='$content')"
    }
}

fun News.toDbObject(): NewsObject {
    return NewsObject().also { news ->
        news.author = this.author
        news.content = this.content
        news.description = this.description
        news.sourceId = this.source?.id
        news.publishedAt = this.publishedAt
        news.sourceName = this.source?.name
        news.url = this.url
        news.urlToImage = this.urlToImage
        news.title = this.title
    }
}

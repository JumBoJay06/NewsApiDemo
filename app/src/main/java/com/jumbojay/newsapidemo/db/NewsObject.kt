package com.jumbojay.newsapidemo.db

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class NewsObject(
    var author: String? = "",
    @PrimaryKey
    var title: String? = "",
    var description: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    var publishedAt: String? = "",
    var content: String? = "",
    var sourceId: String? = "",
    var sourceName: String? = "",
) : RealmObject {
    constructor() : this(title = "0")
    override fun toString(): String {
        return "NewsObject(author='$author', title='$title', description='$description', url='$url', urlToImage='$urlToImage', publishedAt='$publishedAt', content='$content', sourceId=$sourceId, sourceName=$sourceName)"
    }
}
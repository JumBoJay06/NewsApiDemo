package com.jumbojay.newsapidemo.data

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import io.realm.kotlin.types.EmbeddedRealmObject

@Keep
data class NewsSource(
    @Expose
    var id: String = "",
    @Expose
    var name: String = ""
) {
    override fun toString(): String {
        return "Source(id='$id', name='$name')"
    }
}

package com.jumbojay.newsapidemo.data

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import io.realm.kotlin.types.EmbeddedRealmObject

@Keep
class Source(
    @Expose
    val id: String = "",
    @Expose
    val name: String = ""
) : EmbeddedRealmObject

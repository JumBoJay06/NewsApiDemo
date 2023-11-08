package com.jumbojay.newsapidemo.db

import com.jumbojay.newsapidemo.data.News
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class NewsDaoImpl : NewsDao {
    override suspend fun add(news: List<NewsObject>): Boolean {
        return try {
            val realm = Realm.open(RealmConfiguration.create(setOf(NewsObject::class)))
            Timber.tag("Realm").i("open")
            realm.write {
                val old = query<NewsObject>().find()
                Timber.tag("Realm").i("get old")
                delete(old)
                Timber.tag("Realm").i("delete old")
                news.forEach {
                    copyToRealm(it)
                }
                Timber.tag("Realm").i("save")
                true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun findAll(): Flow<ResultsChange<NewsObject>>? {
        return try {
            val realm = Realm.open(RealmConfiguration.create(setOf(NewsObject::class)))
            realm.query(NewsObject::class).asFlow()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun deleteAll(): Boolean {
        return try {
            val realm = Realm.open(RealmConfiguration.create(setOf(NewsObject::class)))
            realm.write {
                val old = query<NewsObject>().find()
                delete(old)
                true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
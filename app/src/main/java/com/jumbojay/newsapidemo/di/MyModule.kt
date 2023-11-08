package com.jumbojay.newsapidemo.di

import com.jumbojay.newsapidemo.ui.NewsRepository
import com.jumbojay.newsapidemo.api.buildWithApi
import com.jumbojay.newsapidemo.api.service.NewsService
import com.jumbojay.newsapidemo.db.NewsDaoImpl
import com.jumbojay.newsapidemo.ui.NewsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { getWebService<NewsService>(10L) }
}

val databaseModule = module {
    single { NewsDaoImpl() }
}

val repositoryModule = module {
    single { NewsRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { NewsViewModel(get(), androidApplication()) }
}

inline  fun <reified T> getWebService(
    readTimeout: Long
): T = Retrofit.Builder()
    .buildWithApi(readTimeout)

val myModules = listOf(
    apiModule,
    databaseModule,
    repositoryModule,
    viewModelModule
)
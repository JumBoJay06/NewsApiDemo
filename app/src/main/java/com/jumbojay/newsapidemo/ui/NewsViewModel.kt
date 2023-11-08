package com.jumbojay.newsapidemo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jumbojay.newsapidemo.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsViewModel(private val newsRepository: NewsRepository, application: Application) :
    AndroidViewModel(application) {

    private val _updateStatus: MutableLiveData<UpdateStatus> = MutableLiveData()
    val updateStatus: LiveData<UpdateStatus>
        get() = _updateStatus

    fun newsFlow() = newsRepository.newsFlow()

    fun updateNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _updateStatus.postValue(UpdateStatus.Loading)
                Timber.tag("updateNews").i("Loading")
                newsRepository.updateNews("us", BuildConfig.NEWS_API_KEY)
                Timber.tag("updateNews").i("total: Success")
                _updateStatus.postValue(UpdateStatus.Success)
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.tag("updateNews").i("total: Fail")
                _updateStatus.postValue(UpdateStatus.Fail(e))
            }
        }
    }

    sealed class UpdateStatus {
        data object Loading : UpdateStatus()
        class Fail(e: Exception) : UpdateStatus()
        data object Success : UpdateStatus()
    }
}
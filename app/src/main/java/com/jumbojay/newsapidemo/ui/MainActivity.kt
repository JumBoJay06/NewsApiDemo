package com.jumbojay.newsapidemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumbojay.newsapidemo.R
import com.jumbojay.newsapidemo.databinding.ActivityMainBinding
import io.realm.kotlin.ext.copyFromRealm
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NewsViewModel by viewModel()

    private val newsListAdapter: NewsListAdapter by lazy { NewsListAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.list.adapter = newsListAdapter
        binding.list.layoutManager = LinearLayoutManager(this)

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.updateNews()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateStatus.observe(this) {
            when (it) {
                is NewsViewModel.UpdateStatus.Fail -> binding.swipeRefresh.isRefreshing = false
                NewsViewModel.UpdateStatus.Loading -> binding.swipeRefresh.isRefreshing = true
                NewsViewModel.UpdateStatus.Success -> binding.swipeRefresh.isRefreshing = false
            }
        }
        viewModel.newsFlow()?.asLiveData(Dispatchers.Main)?.observe(this) {
            Timber.tag("db flow").i("total: ${it.list.size}")
            newsListAdapter.submitList(it.list.copyFromRealm())
        }

        viewModel.updateNews()
    }
}
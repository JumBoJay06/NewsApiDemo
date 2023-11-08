package com.jumbojay.newsapidemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jumbojay.newsapidemo.R
import com.jumbojay.newsapidemo.databinding.ItemViewBinding
import com.jumbojay.newsapidemo.db.NewsObject
import timber.log.Timber

class NewsListAdapter : ListAdapter<NewsObject, NewsListAdapter.ItemViewHolder>(NewsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsObject: NewsObject) {
            binding.news = newsObject
            Timber.tag("asdasd").d("url: ${newsObject.urlToImage}")
            Glide.with(binding.itemImg)
                .load(newsObject.urlToImage)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_newspaper)
                .error(R.drawable.ic_newspaper)
                .into(binding.itemImg)
        }
    }

    private object NewsDiffCallback : DiffUtil.ItemCallback<NewsObject>() {
        override fun areItemsTheSame(oldItem: NewsObject, newItem: NewsObject): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: NewsObject, newItem: NewsObject): Boolean =
            oldItem.title == newItem.title

    }
}
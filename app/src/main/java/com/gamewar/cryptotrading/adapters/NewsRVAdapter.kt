package com.gamewar.cryptotrading.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gamewar.cryptoapi.models.entities.NewsData
import com.gamewar.cryptotrading.R
import com.gamewar.cryptotrading.databinding.RvNewsItemBinding
import com.gamewar.cryptotrading.utils.DateConverter

class NewsRVAdapter(private val listener: INewsRVAdapter): ListAdapter<NewsData, NewsRVAdapter.NewsViewHolder>(NewsDiffCallback()) {

    class NewsDiffCallback: DiffUtil.ItemCallback<NewsData>() {
        override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }

    inner class NewsViewHolder(val binding: RvNewsItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = RvNewsItemBinding.inflate(inflater)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.tvNewsTitle.text = currentItem.title
        holder.binding.tvNewsBody.text = currentItem.body
        holder.binding.tvSourceName.text = currentItem.sourceInfo?.name

        if (!currentItem.sourceInfo?.img.isNullOrEmpty()) {
            Glide.with(holder.binding.root.context).load(currentItem.sourceInfo?.img)
                .into(holder.binding.ivSourceImage)
        } else {
            Glide.with(holder.binding.root.context).load(R.drawable.ic_android).into(holder.binding.ivSourceImage)
        }

        holder.binding.tvNewsTime.text = DateConverter.getTimeAgo(currentItem.publishedOn!!.toLong())

        holder.binding.rlRootNewsItem.setOnClickListener {
            currentItem.url?.let { it1 -> listener.onNewsArticleClicked(it1) }
        }
    }
}

interface INewsRVAdapter {
    fun onNewsArticleClicked(url: String)
}
package com.dggorbachev.newsfeedapp.feature.main_screen.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dggorbachev.newsfeedapp.databinding.ActivityMainBinding
import com.dggorbachev.newsfeedapp.databinding.ItemArticleBinding
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class ArticlesAdapter : AsyncListDifferDelegationAdapter<Article>(ArticleDiffUtilCallback()) {
    init {
        delegatesManager.addDelegate(articleAdapterDelegate())
    }

    private fun articleAdapterDelegate() =
        adapterDelegateViewBinding<Article, Article, ItemArticleBinding>(
            { layoutInflater, parent -> ItemArticleBinding.inflate(layoutInflater, parent, false) }
        ) {
            bind {
                binding.apply {
                    authorTextView.text = item.author
                    descriptionTextView.text = item.description
                }
            }
        }

    class ArticleDiffUtilCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}
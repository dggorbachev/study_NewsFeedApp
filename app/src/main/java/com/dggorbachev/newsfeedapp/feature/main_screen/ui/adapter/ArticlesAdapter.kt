package com.dggorbachev.newsfeedapp.feature.main_screen.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dggorbachev.newsfeedapp.MainActivity
import com.dggorbachev.newsfeedapp.R
import com.dggorbachev.newsfeedapp.base.utils.setThrottledClickListener
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article
import com.dggorbachev.newsfeedapp.feature.main_screen.ui.MainScreenFragment
import com.dggorbachev.newsfeedapp.feature.main_screen.ui.MainScreenViewModel

class ArticlesAdapter(
    private var articleList: List<Article>,
    private val onItemClick: (article: Article) -> Unit,
    private val onBookmarkClick: (article: Article) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val description: TextView
        val publishedAt: TextView
        val image: ImageView
        val favoriteIcon: CheckBox

        init {
            this.title = view.findViewById<TextView>(R.id.tvTitle)
            this.description = view.findViewById<TextView>(R.id.tvDescription)
            this.publishedAt = view.findViewById<TextView>(R.id.tvPublishedAt)
            this.image = view.findViewById<ImageView>(R.id.ivArticleImage)
            this.favoriteIcon = view.findViewById<CheckBox>(R.id.cbBookmarkAdd)
        }
    }

    lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = articleList[position].title
        holder.description.text = articleList[position].description
        holder.publishedAt.text = articleList[position].publishedAt

        Glide.with(view)
            .load(articleList[position].urlToImage)
            .centerCrop()
            .placeholder(R.drawable.headline_news_logo_png_transparent)
            .into(holder.image)

        holder.favoriteIcon.setThrottledClickListener {
            onBookmarkClick(articleList[position])
        }

        holder.itemView.setThrottledClickListener {
            onItemClick(articleList[position])
        }

        holder.favoriteIcon.apply {
            if (articleList[position].isFavorite) {
                holder.favoriteIcon.setButtonDrawable(R.drawable.favorite_fill_icon)
            } else {
                holder.favoriteIcon.setButtonDrawable(R.drawable.favorite_icon)
            }
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun updateArticles(newArticles: List<Article>) {
        articleList = newArticles
        notifyDataSetChanged()
    }
}
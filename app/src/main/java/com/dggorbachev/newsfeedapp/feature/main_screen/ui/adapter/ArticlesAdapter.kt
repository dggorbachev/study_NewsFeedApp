package com.dggorbachev.newsfeedapp.feature.main_screen.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dggorbachev.newsfeedapp.R
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article

class ArticlesAdapter(private var articleList: List<Article>) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author: TextView
        val title: TextView

        init {
            this.author = view.findViewById<TextView>(R.id.authorTextView)
            this.title = view.findViewById<TextView>(R.id.descriptionTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.author.text = articleList[position].author
        holder.title.text = articleList[position].title
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun updateArticles(newArticles: List<Article>) {
        articleList = newArticles
        notifyDataSetChanged()
    }
}
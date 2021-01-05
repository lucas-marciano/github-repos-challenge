package com.lucasdam.githubchanllenge.ui.main.home

import android.view.View
import com.lucasdam.githubchanllenge.R
import com.lucasdam.githubchanllenge.mvvm.BaseAdapter
import com.lucasdam.githubchanllenge.shared.extensions.loadImage
import com.lucasdam.githubchanllenge.shared.model.view.Repository
import kotlinx.android.synthetic.main.repository_item_layout.view.*

/**
 * @author Lucas Marciano on 03/01/21.
 */

class HomeAdapter : BaseAdapter<Repository>() {

    lateinit var callback: (Repository) -> Unit

    override fun getLayoutResId(viewType: Int) = R.layout.repository_item_layout

    override fun getViewHolder(itemView: View, viewType: Int) = HomeViewHolder(itemView, callback)

    inner class HomeViewHolder(itemView: View, private val callback: (Repository) -> Unit) :
        BaseViewHolder<Repository>(itemView) {
        override fun bind(item: Repository, position: Int) {
            itemView.tv_item_tv_name.text = item.name
            itemView.tv_total_stars.text = item.stargazersCount
            itemView.tv_total_forks.text = item.forksCount
            itemView.tv_author_name.text = item.owner.login
            itemView.iv_author_avatar.loadImage(item.owner.avatar_url)
            itemView.setOnClickListener { callback(item) }
        }
    }
}

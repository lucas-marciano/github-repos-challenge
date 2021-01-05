package com.lucasdam.githubchanllenge.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.lucasdam.githubchanllenge.mvvm.BaseAdapter.BaseViewHolder

/**
 * @author Lucas Marciano on 05/04/20.
 */

abstract class BaseAdapter<ItemType> : RecyclerView.Adapter<BaseViewHolder<ItemType>>() {

    internal var list = listOf<ItemType>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    @LayoutRes
    abstract fun getLayoutResId(viewType: Int): Int

    abstract fun getViewHolder(itemView: View, viewType: Int): BaseViewHolder<ItemType>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemType> =
        getViewHolder(inflate(getLayoutResId(viewType), parent), viewType)

    override fun onBindViewHolder(holder: BaseViewHolder<ItemType>, position: Int) =
        holder.bind(list[position], position)

    override fun getItemCount(): Int = list.size

    fun getItem(position: Int): ItemType = list[position]

    fun isEmpty(): Boolean = list.isEmpty()

    fun isTheLastItem(position: Int): Boolean = position == list.size - 1

    @NonNull
    protected fun inflate(@LayoutRes layoutRes: Int, @Nullable parent: ViewGroup): View =
        LayoutInflater.from(parent.context)
            .inflate(layoutRes, parent, false)

    open fun detach() {}

    abstract class BaseViewHolder<ItemType>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bind(item: ItemType, position: Int)
    }
}

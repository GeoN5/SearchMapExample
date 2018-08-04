package com.example.geonho.taehwanstudy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recent_search.*


interface OnItemClickListener {
    fun onItemClick(items: String)
    fun onItemDelete(position: Int)
}


abstract class BaseViewHolder<T: Any>(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    abstract fun bind(item: T)
}

class ViewHolder(itemView: View,val listener: OnItemClickListener) : BaseViewHolder<String>(itemView) {

    override fun bind(item: String) {
        recentText.text = item
        recentText.setOnClickListener {
            listener.onItemClick(item)
        }
        deleteButton.setOnClickListener {
            listener.onItemDelete(adapterPosition)
        }
    }
}

class RecentViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {

    override fun bind(item: String) {
        recentText.text = item
    }
}

class RecyclerAdapter(val items : List<AdapterItem>,val context:Context,val listener: OnItemClickListener) : RecyclerView.Adapter<BaseViewHolder<String>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        return when (viewType) {
            100 -> RecentViewHolder(LayoutInflater.from(context).inflate(R.layout.recent_search, parent, false))
            else ->ViewHolder(LayoutInflater.from(context).inflate(R.layout.recent_search, parent, false),listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<String>, position: Int) {
        //holder.recentText.text = items[position]
        holder.bind(items[position].name)
    }
}

data class AdapterItem(val name: String, val viewType: Int)


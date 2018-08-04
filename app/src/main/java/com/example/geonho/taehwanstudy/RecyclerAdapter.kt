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

class ViewHolder(override val containerView: View,val listener: OnItemClickListener) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: String) {
        recentText.text = item
        recentText.setOnClickListener {
            listener.onItemClick(item)
        }
        deleteButton.setOnClickListener {
            listener.onItemDelete(adapterPosition)
        }
    }
}

class RecyclerAdapter(val items : List<String>,val context:Context,val listener: OnItemClickListener) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recent_search, parent, false),listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.recentText.text = items[position]
        holder.bind(items[position])
    }
}

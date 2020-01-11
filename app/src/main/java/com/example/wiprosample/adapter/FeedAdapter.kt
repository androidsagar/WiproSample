package com.example.wiprosample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.wiprosample.R
import com.example.wiprosample.data_models.Feed
import kotlinx.android.synthetic.main.row_feed.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class FeedAdapter:RecyclerView.Adapter<FeedAdapter.Holder>(),KoinComponent {

    private val glide:RequestManager by inject()
    private var feedList = listOf<Feed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.row_feed,parent,false))
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.run {
            feedList[position].let {
                textTitle.text = it.title
                textDescription.text = it.description
                it.imageUrl?.let {
                    glide.load(it).placeholder(R.drawable.place_holder).into(imageFeed)
                }
            }
        }
    }

    fun setData(feedList: List<Feed>){
        this.feedList = feedList
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View):RecyclerView.ViewHolder(itemView)

}
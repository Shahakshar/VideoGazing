package com.example.videogazing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.videogazing.api.model.Item

class VideoRecyclerView(
    private val list: List<Item>
): RecyclerView.Adapter<VideoRecyclerView.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.videoImg)
        val videoTitle: TextView = itemView.findViewById(R.id.videoDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val imageUrl = item.snippet.thumbnails.high.url
        val videoTitle = item.snippet.title
        holder.imageView.load(imageUrl)
        holder.videoTitle.text = videoTitle
    }

}
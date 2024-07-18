package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Podcast
import com.squareup.picasso.Picasso

class PodcastsAdapter(private val podcasts: List<Podcast>) : RecyclerView.Adapter<PodcastsAdapter.PodcastViewHolder>() {

    class PodcastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val podcastPictureMedium: ImageView = itemView.findViewById(R.id.image_view_podcast_picture_medium)
        val podcastTitle: TextView = itemView.findViewById(R.id.text_view_podcast_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_podcasts, parent, false)
        return PodcastViewHolder(view)
    }

    override fun onBindViewHolder(holder: PodcastViewHolder, position: Int) {
        val podcast = podcasts[position]
        holder.podcastTitle.text = podcast.title
        Picasso.get().load(podcast.pictureMedium).into(holder.podcastPictureMedium)
    }

    override fun getItemCount(): Int = podcasts.size

}
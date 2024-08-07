package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Podcast
import com.squareup.picasso.Picasso

class PodcastsAdapter(private var podcasts: List<Podcast>) : RecyclerView.Adapter<PodcastsAdapter.PodcastViewHolder>() {

    private var onItemClickListener: ((Podcast) -> Unit)? = null

    inner class PodcastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val podcastPictureMedium: ImageView = view.findViewById(R.id.image_view_podcast_picture_medium)
        val podcastTitle: TextView = view.findViewById(R.id.text_view_podcast_title)
        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(podcasts[position])
                }
            }
        }
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

    fun updateData(newData: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = podcasts.size
            override fun getNewListSize() = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return podcasts[oldItemPosition].id == (newData[newItemPosition] as Podcast).id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return podcasts[oldItemPosition].id == (newData[newItemPosition] as Podcast).id
            }
        })
        podcasts = newData as List<Podcast>
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(listener: (Podcast) -> Unit) {
        onItemClickListener = listener
    }

}
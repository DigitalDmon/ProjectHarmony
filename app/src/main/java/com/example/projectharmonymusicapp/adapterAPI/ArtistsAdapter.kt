package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Artist
import com.squareup.picasso.Picasso

class ArtistsAdapter(private var artists: List<Artist>) : RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>() {

    private var onItemClickListener: ((Artist) -> Unit)? = null

    inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val artistPictureMedium: ImageView = view.findViewById(R.id.image_view_artist_picture_big)
        val artistName: TextView = view.findViewById(R.id.text_view_artist_name)
        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(artists[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_artists, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.artistName.text = artist.name
        Picasso.get().load(artist.pictureMedium).into(holder.artistPictureMedium)
    }

    override fun getItemCount(): Int = artists.size

    fun updateData(newData: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = artists.size
            override fun getNewListSize() = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return artists[oldItemPosition].id == (newData[newItemPosition] as Artist).id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return artists[oldItemPosition].id == (newData[newItemPosition] as Artist).id
            }
        })
        artists = newData as List<Artist>
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(listener: (Artist) -> Unit) {
        onItemClickListener = listener
    }

}
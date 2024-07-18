package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Artist
import com.squareup.picasso.Picasso

class ArtistsAdapter(private val artists: List<Artist>) : RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistPictureMedium: ImageView = itemView.findViewById(R.id.image_view_artist_picture_medium)
        val artistName: TextView = itemView.findViewById(R.id.text_view_artist_name)
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
}

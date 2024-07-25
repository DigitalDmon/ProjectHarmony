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

    inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistPictureMedium: ImageView = itemView.findViewById(R.id.image_view_artist_picture_big)
        val artistName: TextView = itemView.findViewById(R.id.text_view_artist_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_artists, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.artistName.text = artist.name
        Picasso.get().load(artist.pictureBig).into(holder.artistPictureMedium)
    }

    override fun getItemCount(): Int = artists.size

    fun updateData(newData: List<Any>) {
        // Calcular las diferencias entre la lista actual y la nueva lista (puedes usar DiffUtil para esto)
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = artists.size
            override fun getNewListSize() = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return artists[oldItemPosition].id == newData[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return artists[oldItemPosition] == newData[newItemPosition]
            }
        })

        // Actualizar la lista
        artists = newData as List<Artist>

        // Aplicar los cambios al adaptador
        diffResult.dispatchUpdatesTo(this)
    }

}

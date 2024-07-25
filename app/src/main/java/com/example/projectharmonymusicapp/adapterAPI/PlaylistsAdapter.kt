package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Playlist
import com.squareup.picasso.Picasso

class PlaylistsAdapter(private var playlists: List<Playlist>) : RecyclerView.Adapter<PlaylistsAdapter.PlaylistViewHolder>() {

    inner class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playlistPictureMedium: ImageView = itemView.findViewById(R.id.image_view_playlist_picture_medium)
        val playlistTitle: TextView = itemView.findViewById(R.id.text_view_playlist_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_playlists, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]
        holder.playlistTitle.text = playlist.title
        Picasso.get().load(playlist.pictureBig).into(holder.playlistPictureMedium)
    }

    override fun getItemCount(): Int = playlists.size

    fun updateData(newData: List<Any>) {
        // Calcular las diferencias entre la lista actual y la nueva lista (puedes usar DiffUtil para esto)
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = playlists.size
            override fun getNewListSize() = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return playlists[oldItemPosition].id == newData[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return playlists[oldItemPosition] == newData[newItemPosition]
            }
        })

        // Actualizar la lista
        playlists = newData as List<Playlist>

        // Aplicar los cambios al adaptador
        diffResult.dispatchUpdatesTo(this)
    }

    
}
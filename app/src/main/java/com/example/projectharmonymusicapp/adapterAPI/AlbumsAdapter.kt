package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Album
import com.squareup.picasso.Picasso

class AlbumsAdapter(private var albums: List<Album>) : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val albumCover: ImageView = view.findViewById(R.id.image_view_album_cover)
        val albumTitle: TextView = view.findViewById(R.id.text_view_album_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_albums, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.albumTitle.text = album.title
        Picasso.get().load(album.coverBig).into(holder.albumCover)
    }

    override fun getItemCount(): Int = albums.size

    fun updateData(newData: List<Any>) {
        // Calcular las diferencias entre la lista actual y la nueva lista (puedes usar DiffUtil para esto)
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = albums.size
            override fun getNewListSize() = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return albums[oldItemPosition].id == newData[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return albums[oldItemPosition] == newData[newItemPosition]
            }
        })

        // Actualizar la lista
        albums = newData as List<Album>

        // Aplicar los cambios al adaptador
        diffResult.dispatchUpdatesTo(this)
    }

}

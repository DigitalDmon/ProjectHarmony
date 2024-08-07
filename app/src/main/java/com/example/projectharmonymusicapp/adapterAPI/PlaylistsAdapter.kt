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

    private var onItemClickListener: ((Playlist) -> Unit)? = null

    inner class PlaylistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playlistPictureMedium: ImageView = view.findViewById(R.id.image_view_playlist_picture_medium)
        val playlistTitle: TextView = view.findViewById(R.id.text_view_playlist_title)
        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(playlists[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_playlists, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]
        holder.playlistTitle.text = playlist.title
        Picasso.get().load(playlist.pictureMedium).into(holder.playlistPictureMedium)
    }

    override fun getItemCount(): Int = playlists.size

    fun updateData(newData: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = playlists.size
            override fun getNewListSize() = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return playlists[oldItemPosition].id == (newData[newItemPosition] as Playlist).id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return playlists[oldItemPosition].id == (newData[newItemPosition] as Playlist).id
            }
        })
        playlists = newData as List<Playlist>
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(listener: (Playlist) -> Unit) {
        onItemClickListener = listener
    }
    
}
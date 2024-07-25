package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Track
import com.squareup.picasso.Picasso

class TracksAdapter(private var tracks: List<Track>) : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {

    inner class TrackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val trackPicture: ImageView = view.findViewById(R.id.image_view_track_picture_medium)
        val trackTitle: TextView = view.findViewById(R.id.text_view_track_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_tracks, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = tracks[position]
        holder.trackTitle.text = track.title
        Picasso.get().load(track.album.coverBig).into(holder.trackPicture)
    }

    override fun getItemCount(): Int = tracks.size

    fun updateData(newData: List<Any>) {
        // Calcular las diferencias entre la lista actual y la nueva lista (puedes usar DiffUtil para esto)
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = tracks.size
            override fun getNewListSize() = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return tracks[oldItemPosition].id == newData[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return tracks[oldItemPosition] == newData[newItemPosition]
            }
        })

        // Actualizar la lista
        tracks = newData as List<Track>

        // Aplicar los cambios al adaptador
        diffResult.dispatchUpdatesTo(this)
    }

}

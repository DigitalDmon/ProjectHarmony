package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Track
import com.squareup.picasso.Picasso

class TracksAdapter(private val tracks: List<Track>) : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {

    class TrackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val trackPictureMedium: ImageView = view.findViewById(R.id.image_view_track_picture_medium)
        val trackTitle: TextView = view.findViewById(R.id.text_view_track_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_tracks, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = tracks[position]
        holder.trackTitle.text = track.title
        Picasso.get().load(track.album.coverMedium).into(holder.trackPictureMedium)
    }

    override fun getItemCount(): Int = tracks.size
}

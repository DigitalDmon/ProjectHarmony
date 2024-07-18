package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Search
import com.squareup.picasso.Picasso

class SearchAdapter(private val searchResults: List<Search>): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val searchArtistName: TextView = itemView.findViewById(R.id.text_view_search_artist_name)
        val searchTrackTitle: TextView = itemView.findViewById(R.id.text_view_search_track_title)
        val searchAlbumTitle: TextView = itemView.findViewById(R.id.text_view_search_album_title)
        val searchArtistPicture: ImageView = itemView.findViewById(R.id.image_view_search_artist_picture)
        val searchTrackPictureMedium: ImageView = itemView.findViewById(R.id.image_view_search_track_cover)
        val searchAlbumCover: ImageView = itemView.findViewById(R.id.image_view_search_album_cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val searchResult = searchResults[position]

        // Manejo de artista
        searchResult.artist?.let { artist ->
            holder.searchArtistName.text = artist.name
            holder.searchArtistPicture.visibility = View.VISIBLE
            Picasso.get().load(artist.pictureMedium).into(holder.searchArtistPicture)
        } ?: run {
            holder.searchArtistName.visibility = View.GONE
            holder.searchArtistPicture.visibility = View.GONE
        }

        // Manejo de pista
        searchResult.track?.let { track ->
            holder.searchTrackTitle.text = track.title
            holder.searchTrackPictureMedium.visibility = View.VISIBLE
            Picasso.get().load(track.pictureMedium).into(holder.searchTrackPictureMedium)
        } ?: run {
            holder.searchTrackTitle.visibility = View.GONE
            holder.searchTrackPictureMedium.visibility = View.GONE
        }

        // Manejo de álbum
        searchResult.album?.let { album ->
            holder.searchAlbumTitle.text = album.title
            holder.searchAlbumCover.visibility = View.VISIBLE
            Picasso.get().load(album.coverMedium).into(holder.searchAlbumCover)
        } ?: run {
            holder.searchAlbumTitle.visibility = View.GONE
            holder.searchAlbumCover.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = searchResults.size
}

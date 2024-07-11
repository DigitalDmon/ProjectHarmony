package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Genres
import com.squareup.picasso.Picasso

class GenresAdapter(private val genres: List<Genres>) : RecyclerView.Adapter<GenresAdapter.GenreViewHolder>() {

    class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreName: TextView = view.findViewById(R.id.text_view_genre)
        val genreImage: ImageView = view.findViewById(R.id.image_view_genre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_genres, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.genreName.text = genre.name
        Picasso.get().load(genre.picture).into(holder.genreImage)
    }

    override fun getItemCount(): Int = genres.size
}
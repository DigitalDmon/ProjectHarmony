package com.example.projectharmonymusicapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.adapterAPI.AlbumsAdapter
import com.example.projectharmonymusicapp.adapterAPI.GenresAdapter
import com.example.projectharmonymusicapp.deezerAPI.RetrofitInstance
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var genresAdapter: GenresAdapter
    private lateinit var recyclerViewResutls: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerViewResutls = view.findViewById(R.id.recycler_view_search)
        recyclerViewResutls.layoutManager = GridLayoutManager(context, 2)
        loadGenres()
        return view
    }

    private fun loadGenres() {
        lifecycleScope.launch {
            try {
                val genresResponse = RetrofitInstance.api.getGenres()
                genresAdapter = GenresAdapter(genresResponse.genres)
                recyclerViewResutls.adapter = genresAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
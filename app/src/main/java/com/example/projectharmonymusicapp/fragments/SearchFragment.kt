package com.example.projectharmonymusicapp.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.activities.NavigationActivity
import com.example.projectharmonymusicapp.activities.SignInActivity
import com.example.projectharmonymusicapp.adapterAPI.GenresAdapter
import com.example.projectharmonymusicapp.adapterAPI.SearchAdapter
import com.example.projectharmonymusicapp.dataClasses.Album
import com.example.projectharmonymusicapp.dataClasses.Artist
import com.example.projectharmonymusicapp.dataClasses.Search
import com.example.projectharmonymusicapp.dataClasses.Track
import com.example.projectharmonymusicapp.deezerAPI.RetrofitInstance
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerViewResults: RecyclerView
    private lateinit var editTextSearch: EditText
    private lateinit var imageViewLogOut: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerViewResults = view.findViewById(R.id.recycler_view_search)
        editTextSearch = view.findViewById(R.id.edit_text_search)
        imageViewLogOut = view.findViewById(R.id.image_view_log_out)
        recyclerViewResults.layoutManager = GridLayoutManager(context, 1)

        // Cargar géneros por defecto
        loadGenres()

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    // Mostrar géneros si el campo de búsqueda está vacío
                    loadGenres()
                } else {
                    // Realizar búsqueda si el campo de búsqueda no está vacío
                    searchMusic(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        imageViewLogOut.setOnClickListener {
            val navigationActivity = activity as NavigationActivity
            navigationActivity.deleteData()
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    private fun loadGenres() {
        lifecycleScope.launch {
            try {
                val genresResponse = RetrofitInstance.api.getGenres()
                val genresAdapter = GenresAdapter(genresResponse.genresData)
                recyclerViewResults.adapter = genresAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun searchMusic(query: String) {
        lifecycleScope.launch {
            try {
                val artistResponseDeferred = async {
                    RetrofitInstance.api.searchArtists("artist:\"$query\"")
                }
                val albumResponseDeferred = async {
                    RetrofitInstance.api.searchAlbums("album:\"$query\"")
                }
                val trackResponseDeferred = async {
                    RetrofitInstance.api.searchTracks("track:\"$query\"")
                }

                val artistResponse = artistResponseDeferred.await().searchData
                val albumResponse = albumResponseDeferred.await().searchData
                val trackResponse = trackResponseDeferred.await().searchData

                // Usar Sets para eliminar duplicados
                val uniqueArtists = mutableSetOf<Artist>()
                val uniqueAlbums = mutableSetOf<Album>()
                val uniqueTracks = mutableSetOf<Track>()

                for (artistSearch in artistResponse) {
                    artistSearch.artist?.let {
                        uniqueArtists.add(it)
                    }
                }
                for (albumSearch in albumResponse) {
                    albumSearch.album?.let {
                        uniqueAlbums.add(it)
                    }
                }
                for (trackSearch in trackResponse) {
                    trackSearch.track?.let {
                        uniqueTracks.add(it)
                    }
                }

                // Combina los resultados únicos
                val searchResults = mutableListOf<Search>()

                uniqueArtists.forEach {
                    searchResults.add(Search(it, null, null))
                }
                uniqueTracks.forEach {
                    searchResults.add(Search(null, it, null))
                }
                uniqueAlbums.forEach {
                    searchResults.add(Search(null, null, it))
                }

                searchAdapter = SearchAdapter(searchResults)
                recyclerViewResults.adapter = searchAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

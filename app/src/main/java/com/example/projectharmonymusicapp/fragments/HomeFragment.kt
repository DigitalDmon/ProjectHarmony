package com.example.projectharmonymusicapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.HorizontalScrollView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.adapterAPI.AlbumsAdapter
import com.example.projectharmonymusicapp.deezerAPI.RetrofitInstance
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var albumsAdapter: AlbumsAdapter
    private lateinit var buttonToAlbums: Button
    private lateinit var recyclerViewResults: RecyclerView
    private lateinit var horizontalScrollViewButtonsContainer: HorizontalScrollView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val horizontalScrollViewButtons = LayoutInflater.from(requireContext()).inflate(R.layout.view_buttons, null, false)
        horizontalScrollViewButtonsContainer = view.findViewById(R.id.horizontal_scroll_view_buttons)
        horizontalScrollViewButtonsContainer.addView(horizontalScrollViewButtons)
        recyclerViewResults = view.findViewById(R.id.recycler_view_results)
        buttonToAlbums = horizontalScrollViewButtonsContainer.findViewById(R.id.button_to_albums)
        recyclerViewResults.layoutManager = GridLayoutManager(context, 2)
        buttonToAlbums.setOnClickListener {
            loadAlbums()
        }
        return view
    }

    private fun loadAlbums() {
        lifecycleScope.launch {
            try {
                val albumsResponse = RetrofitInstance.api.getAlbums()
                albumsAdapter = AlbumsAdapter(albumsResponse.albums)
                recyclerViewResults.adapter = albumsAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
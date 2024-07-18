package com.example.projectharmonymusicapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.activities.NavigationActivity
import com.example.projectharmonymusicapp.activities.SignInActivity
import com.example.projectharmonymusicapp.adapterAPI.AlbumsAdapter
import com.example.projectharmonymusicapp.adapterAPI.TracksAdapter
import com.example.projectharmonymusicapp.adapterAPI.ArtistsAdapter
import com.example.projectharmonymusicapp.adapterAPI.PlaylistsAdapter
import com.example.projectharmonymusicapp.adapterAPI.PodcastsAdapter
import com.example.projectharmonymusicapp.adapterAPI.RadioAdapter
import com.example.projectharmonymusicapp.deezerAPI.RetrofitInstance
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var tracksAdapter: TracksAdapter
    private lateinit var albumsAdapter: AlbumsAdapter
    private lateinit var artistsAdapter: ArtistsAdapter
    private lateinit var playlistsAdapter: PlaylistsAdapter
    private lateinit var radioAdapter: RadioAdapter
    private lateinit var podcastsAdapter: PodcastsAdapter
    private lateinit var buttonToTracks: Button
    private lateinit var buttonToAlbums: Button
    private lateinit var buttonToArtists: Button
    private lateinit var buttonToPlaylists: Button
    private lateinit var buttonToRadio: Button
    private lateinit var buttonToPodcasts: Button
    private lateinit var textViewSection: TextView
    private lateinit var recyclerViewResults: RecyclerView
    private lateinit var imageViewLogOut: ImageView
    private lateinit var horizontalScrollViewButtonsContainer: HorizontalScrollView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val horizontalScrollViewButtons = LayoutInflater.from(requireContext()).inflate(R.layout.view_buttons, null, false)
        horizontalScrollViewButtonsContainer = view.findViewById(R.id.horizontal_scroll_view_buttons)
        horizontalScrollViewButtonsContainer.addView(horizontalScrollViewButtons)
        recyclerViewResults = view.findViewById(R.id.recycler_view_results)
        textViewSection = view.findViewById(R.id.text_view_section)
        buttonToTracks = horizontalScrollViewButtonsContainer.findViewById(R.id.button_to_tracks)
        buttonToAlbums = horizontalScrollViewButtonsContainer.findViewById(R.id.button_to_albums)
        buttonToArtists = horizontalScrollViewButtonsContainer.findViewById(R.id.button_to_artist)
        buttonToPlaylists = horizontalScrollViewButtonsContainer.findViewById(R.id.button_to_playlist)
        buttonToRadio = horizontalScrollViewButtonsContainer.findViewById(R.id.button_to_radio)
        buttonToPodcasts = horizontalScrollViewButtonsContainer.findViewById(R.id.button_to_podcasts)
        imageViewLogOut = view.findViewById(R.id.image_view_log_out)
        recyclerViewResults.layoutManager = GridLayoutManager(context, 2)
        loadTracks()
        textViewSection.text = buttonToTracks.text
        buttonToTracks.setOnClickListener {
            loadTracks()
            textViewSection.text = buttonToTracks.text
        }
        buttonToAlbums.setOnClickListener {
            loadAlbums()
            textViewSection.text = buttonToAlbums.text
        }
        buttonToArtists.setOnClickListener {
            loadArtists()
            textViewSection.text = buttonToArtists.text
        }
        buttonToPlaylists.setOnClickListener {
            loadPlaylists()
            textViewSection.text = buttonToPlaylists.text
        }
        buttonToRadio.setOnClickListener {
            loadRadio()
            textViewSection.text = buttonToRadio.text
        }
        buttonToPodcasts.setOnClickListener {
            loadPodcasts()
            textViewSection.text = buttonToPodcasts.text
        }
        imageViewLogOut.setOnClickListener {
            val navigationActivity = activity as NavigationActivity
            navigationActivity.deleteData()
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    private fun loadTracks() {
        lifecycleScope.launch {
            try {
                val tracksResponse = RetrofitInstance.api.getTracks()
                tracksAdapter = TracksAdapter(tracksResponse.tracksData)
                recyclerViewResults.adapter = tracksAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadAlbums() {
        lifecycleScope.launch {
            try {
                val albumsResponse = RetrofitInstance.api.getAlbums()
                albumsAdapter = AlbumsAdapter(albumsResponse.albumsData)
                recyclerViewResults.adapter = albumsAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadArtists() {
        lifecycleScope.launch {
            try {
                val artistsResponse = RetrofitInstance.api.getArtist()
                artistsAdapter = ArtistsAdapter(artistsResponse.artistsData)
                recyclerViewResults.adapter = artistsAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadPlaylists() {
        lifecycleScope.launch {
            try {
                val playlistsResponse = RetrofitInstance.api.getPlaylists()
                playlistsAdapter = PlaylistsAdapter(playlistsResponse.playlistsData)
                recyclerViewResults.adapter = playlistsAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadRadio() {
        lifecycleScope.launch {
            try {
                val radioResponse = RetrofitInstance.api.getRadio()
                radioAdapter = RadioAdapter(radioResponse.radioData)
                recyclerViewResults.adapter = radioAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadPodcasts() {
        lifecycleScope.launch {
            try {
                val podcastsResponse = RetrofitInstance.api.getPodcasts()
                podcastsAdapter = PodcastsAdapter(podcastsResponse.podcastsData)
                recyclerViewResults.adapter = podcastsAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
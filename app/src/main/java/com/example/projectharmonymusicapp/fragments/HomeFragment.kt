package com.example.projectharmonymusicapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectharmonymusicapp.activities.NavigationActivity
import com.example.projectharmonymusicapp.activities.SignInActivity
import com.example.projectharmonymusicapp.activities.TracksDetailsActivity
import com.example.projectharmonymusicapp.adapterAPI.*
import com.example.projectharmonymusicapp.databinding.FragmentHomeBinding
import com.example.projectharmonymusicapp.databinding.ViewButtonsBinding
import com.example.projectharmonymusicapp.deezerAPI.RetrofitInstance
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var buttonsBinding: ViewButtonsBinding

    private val tracksAdapter = TracksAdapter(emptyList())
    private val albumsAdapter = AlbumsAdapter(emptyList())
    private val artistsAdapter = ArtistsAdapter(emptyList())
    private val playlistsAdapter = PlaylistsAdapter(emptyList())
    private val radioAdapter = RadioAdapter(emptyList())
    private val podcastsAdapter = PodcastsAdapter(emptyList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        buttonsBinding = ViewButtonsBinding.inflate(inflater, container, false)
        binding.horizontalScrollViewButtonsContainer.addView(buttonsBinding.root)
        setupUI()
        setupListeners()
        setupItemClickListeners()
        loadContent(ContentType.TRACKS)
        return binding.root
    }

    private fun setupUI() {
        binding.recyclerViewResults.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewResults.adapter = tracksAdapter
        binding.textViewSection.text = buttonsBinding.buttonToTracks.text
    }

    private fun setupListeners() {
        buttonsBinding.apply {
            buttonToTracks.setOnClickListener { loadContent(ContentType.TRACKS) }
            buttonToAlbums.setOnClickListener { loadContent(ContentType.ALBUMS) }
            buttonToArtists.setOnClickListener { loadContent(ContentType.ARTISTS) }
            buttonToPlaylists.setOnClickListener { loadContent(ContentType.PLAYLISTS) }
            buttonToRadio.setOnClickListener { loadContent(ContentType.RADIO) }
            buttonToPodcasts.setOnClickListener { loadContent(ContentType.PODCASTS) }
        }

        binding.imageViewLogOut.setOnClickListener { logOut() }
    }

    private fun setupItemClickListeners() {
        tracksAdapter.setOnItemClickListener { track ->
            val intent = Intent(activity, TracksDetailsActivity::class.java)
            intent.putExtra("TRACK_ID", track.id)
            intent.putExtra("TRACK_TITLE", track.title)
            intent.putExtra("IMAGE_URL", track.album.coverMedium)
            intent.putExtra("TRACK_DURATION", track.duration)
            intent.putExtra("TRACK_PREVIEW", track.preview)
            startActivity(intent)
        }
        albumsAdapter.setOnItemClickListener { album ->
            val intent = Intent(activity, TracksDetailsActivity::class.java)
            intent.putExtra("ALBUM_ID", album.id)
            startActivity(intent)
        }
        artistsAdapter.setOnItemClickListener { artist ->
            val intent = Intent(activity, TracksDetailsActivity::class.java)
            intent.putExtra("ARTIST_ID", artist.id)
            startActivity(intent)
        }
        playlistsAdapter.setOnItemClickListener { playlist ->
            val intent = Intent(activity, TracksDetailsActivity::class.java)
            intent.putExtra("PLAYLIST_ID", playlist.id)
            startActivity(intent)
        }
        radioAdapter.setOnItemClickListener { radio ->
            val intent = Intent(activity, TracksDetailsActivity::class.java)
            intent.putExtra("RADIO_ID", radio.id)
            startActivity(intent)
        }
        podcastsAdapter.setOnItemClickListener { podcast ->
            val intent = Intent(activity, TracksDetailsActivity::class.java)
            intent.putExtra("PODCAST_ID", podcast.id)
            startActivity(intent)
        }
    }

    private fun loadContent(type: ContentType) {
        lifecycleScope.launch {
            try {
                val response = when (type) {
                    ContentType.TRACKS -> RetrofitInstance.api.getTracks().tracksData
                    ContentType.ALBUMS -> RetrofitInstance.api.getAlbums().albumsData
                    ContentType.ARTISTS -> RetrofitInstance.api.getArtist().artistsData
                    ContentType.PLAYLISTS -> RetrofitInstance.api.getPlaylists().playlistsData
                    ContentType.RADIO -> RetrofitInstance.api.getRadio().radioData
                    ContentType.PODCASTS -> RetrofitInstance.api.getPodcasts().podcastsData
                }

                val adapter = when (type) {
                    ContentType.TRACKS -> tracksAdapter.apply { updateData(response) }
                    ContentType.ALBUMS -> albumsAdapter.apply { updateData(response) }
                    ContentType.ARTISTS -> artistsAdapter.apply { updateData(response) }
                    ContentType.PLAYLISTS -> playlistsAdapter.apply { updateData(response) }
                    ContentType.RADIO -> radioAdapter.apply { updateData(response) }
                    ContentType.PODCASTS -> podcastsAdapter.apply { updateData(response) }
                }

                binding.recyclerViewResults.adapter = adapter
                binding.textViewSection.text = getSectionText(type)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getSectionText(type: ContentType): String {
        return when (type) {
            ContentType.TRACKS -> buttonsBinding.buttonToTracks.text.toString()
            ContentType.ALBUMS -> buttonsBinding.buttonToAlbums.text.toString()
            ContentType.ARTISTS -> buttonsBinding.buttonToArtists.text.toString()
            ContentType.PLAYLISTS -> buttonsBinding.buttonToPlaylists.text.toString()
            ContentType.RADIO -> buttonsBinding.buttonToRadio.text.toString()
            ContentType.PODCASTS -> buttonsBinding.buttonToPodcasts.text.toString()
        }
    }

    private fun logOut() {
        val navigationActivity = activity as NavigationActivity
        navigationActivity.deleteData()
        val intent = Intent(requireContext(), SignInActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    enum class ContentType {
        TRACKS, ALBUMS, ARTISTS, PLAYLISTS, RADIO, PODCASTS
    }
}

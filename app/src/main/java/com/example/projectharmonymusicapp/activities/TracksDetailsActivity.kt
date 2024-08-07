package com.example.projectharmonymusicapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projectharmonymusicapp.databinding.ActivityTracksDetailsBinding
import com.squareup.picasso.Picasso

class TracksDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTracksDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTracksDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val trackPicture = intent.getStringExtra("IMAGE_URL")
        val trackTitle = intent.getStringExtra("TRACK_TITLE")
        val trackDuration = intent.getStringExtra("TRACK_DURATION")
        val trackPreview = intent.getStringExtra("TRACK_PREVIEW")
        Picasso.get().load(trackPicture).into(binding.imageViewContainer)
        binding.textViewContainer.text = trackTitle
    }
}
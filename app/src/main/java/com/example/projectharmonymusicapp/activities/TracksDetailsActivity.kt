package com.example.projectharmonymusicapp.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.databinding.ActivityTracksDetailsBinding
import com.example.projectharmonymusicapp.databinding.ViewAlbumsBinding
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
        Picasso.get().load(trackPicture).into(binding.imageViewContainer)
        binding.textViewContainer.text = trackTitle
    }
}
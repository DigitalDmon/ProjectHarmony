package com.example.projectharmonymusicapp.activities

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.TrackResponse
import com.example.projectharmonymusicapp.deezerAPI.RetrofitInstance
import com.example.projectharmonymusicapp.fragments.HomeFragment
import com.example.projectharmonymusicapp.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NavigationActivity : AppCompatActivity() {

    private lateinit var navigationBar: BottomNavigationView
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var playerContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        val pref = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        pref.putString("email", email)
        pref.putString("provider", provider)
        pref.apply()
        setContentView(R.layout.activity_navigation)
        navigationBar = findViewById(R.id.bottom_navigation_view)
        playerContainer = findViewById(R.id.relativeLayout)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        navigationBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_frag -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.search_frag -> {
                    replaceFragment(SearchFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun deleteData() {
        val pref = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        pref.clear()
        pref.apply()
    }
}

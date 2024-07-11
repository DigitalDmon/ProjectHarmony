package com.example.projectharmonymusicapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.fragments.HomeFragment
import com.example.projectharmonymusicapp.fragments.LibraryFragment
import com.example.projectharmonymusicapp.fragments.ProfileFragment
import com.example.projectharmonymusicapp.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {

    private lateinit var navigationBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        navigationBar = findViewById(R.id.bottom_navigation_view)
        // Establishing the default fragment
//        if (savedInstanceState == null) {
//            replaceFragment(HomeFragment())
//        }
        //Listener for the navigation bar
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
                R.id.library_frag -> {
                    replaceFragment(LibraryFragment())
                    true
                }
                R.id.profile_frag -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    //Function to replace the fragments
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

}
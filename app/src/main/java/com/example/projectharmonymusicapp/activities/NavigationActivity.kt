package com.example.projectharmonymusicapp.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.fragments.HomeFragment
import com.example.projectharmonymusicapp.fragments.LibraryFragment
import com.example.projectharmonymusicapp.fragments.ProfileFragment
import com.example.projectharmonymusicapp.fragments.SearchFragment
import com.example.projectharmonymusicapp.providers.ProviderType
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {

    private lateinit var navigationBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // SAVING DATA
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        val pref = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        pref.putString("email", email)
        pref.putString("provider", provider)
        pref.apply()
        session()
        setContentView(R.layout.activity_navigation)
        navigationBar = findViewById(R.id.bottom_navigation_view)
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

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun session() {
        val pref = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = pref.getString("email", null)
        val provider = pref.getString("provider", null)
        if (email != null && provider != null) {
            SignInActivity().showNavigationActivity(email, ProviderType.valueOf(provider))
        }
    }

    fun deleteData() {
        val pref = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        pref.clear()
        pref.apply()
    }

}
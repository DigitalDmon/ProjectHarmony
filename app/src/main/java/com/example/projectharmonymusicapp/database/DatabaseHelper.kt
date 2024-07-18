package com.example.projectharmonymusicapp.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DatabaseHelper {

    private var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var tableUsers = "users"
    private var tableFavorites = "favorites"
    private var tableSongs = "songs"
    private var tableAlbums = "albums"
    private var tablePlaylists = "playlists"

   private fun getUID(): String {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        return uid.toString()
    }

    fun addSongToFavorites(songId: String) {
        val uid = getUID()
        databaseReference.child(tableUsers).child(uid).child(tableFavorites).child(songId).setValue(true)
    }

    fun removeSongFromFavorites(songId: String) {
        val uid = getUID()
        databaseReference.child(tableUsers).child(uid).child(tableFavorites).child(songId).removeValue()
    }

}
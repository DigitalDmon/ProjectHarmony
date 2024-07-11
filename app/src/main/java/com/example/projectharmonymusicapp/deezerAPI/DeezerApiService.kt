package com.example.projectharmonymusicapp.deezerAPI

import com.example.projectharmonymusicapp.dataClasses.Albums
import com.example.projectharmonymusicapp.dataClasses.AlbumsResponse
import com.example.projectharmonymusicapp.dataClasses.GenresResponse
import retrofit2.Call
import retrofit2.http.GET

interface DeezerApiService {

    @GET("chart/0/albums")
    suspend fun getAlbums(): AlbumsResponse

    @GET("genre")
    suspend fun getGenres(): GenresResponse

}
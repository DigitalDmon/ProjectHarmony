package com.example.projectharmonymusicapp.deezerAPI

import com.example.projectharmonymusicapp.dataClasses.AlbumResponse
import com.example.projectharmonymusicapp.dataClasses.ArtistResponse
import com.example.projectharmonymusicapp.dataClasses.GenreResponse
import com.example.projectharmonymusicapp.dataClasses.PlaylistResponse
import com.example.projectharmonymusicapp.dataClasses.PodcastResponse
import com.example.projectharmonymusicapp.dataClasses.RadioResponse
import com.example.projectharmonymusicapp.dataClasses.SearchResponse
import com.example.projectharmonymusicapp.dataClasses.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerApiService {

    @GET("chart/0/albums")
    suspend fun getAlbums(): AlbumResponse

    @GET("chart/0/playlists")
    suspend fun getPlaylists(): PlaylistResponse

    @GET("chart/0/artists")
    suspend fun getArtist(): ArtistResponse

    @GET("chart/0/podcasts")
    suspend fun getPodcasts(): PodcastResponse

    @GET("chart/0/tracks")
    suspend fun getTracks(): TrackResponse

    @GET("radio/top")
    suspend fun getRadio(): RadioResponse

    @GET("genre")
    suspend fun getGenres(): GenreResponse

//    @GET("artists/{id}")
//    suspend fun getArtists(@Path("id") artistId: String): ArtistResponse
//
//    @GET("artists/{id}/albums")
//    suspend fun getArtistAlbums(@Path("id") artistId: String): AlbumResponse

//    @GET("album/{id}")
//    suspend fun getAlbumDetails(@Path("id") albumId: String): AlbumDetailsResponse
//
//    @GET("playlist/{id}")
//    suspend fun getPlaylistDetails(@Path("id") playlistId: String): PlaylistDetailsResponse
//
//    @GET("track/{id}")
//    fun getTrack(@Path("id") albumId: String): TrackResponse

    @GET("search")
    suspend fun searchTracks(@Query("q") query: String): SearchResponse
    //search?q={query}

    @GET("search")
    suspend fun searchAlbums(@Query("q") query: String): SearchResponse

    @GET("search")
    suspend fun searchArtists(@Query("q") query: String): SearchResponse


}
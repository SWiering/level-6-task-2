package com.simon.popularmovies.services

import com.simon.popularmovies.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("https://api.themoviedb.org/3/discover/movie?api_key=b6fe4fe1ed225d638b48eb8f21e652df&language=en-US&sort_by=popularity.desc&include_video=false")
    fun getMovies(@Query("year") year: String): Call<MovieList>
}
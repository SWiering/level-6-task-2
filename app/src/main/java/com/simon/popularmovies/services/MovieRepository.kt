package com.simon.popularmovies.services

import android.content.Context
import com.simon.popularmovies.model.MovieList
import retrofit2.Call

class MovieRepository(var context: Context) {
    private val movieApi: MovieApiService = MovieApi.createApi()

    fun getMovies(year: String): Call<MovieList> {
        return movieApi.getMovies(year)
    }
}
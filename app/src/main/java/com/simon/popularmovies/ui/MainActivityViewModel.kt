package com.simon.popularmovies.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.simon.popularmovies.model.MovieList
import com.simon.popularmovies.services.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepository = MovieRepository(application.applicationContext)
    val error = MutableLiveData<String>()
    val moviesList = MutableLiveData<MovieList>()

    fun getMovies(year: String) {
        moviesRepository.getMovies(year).enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.isSuccessful) moviesList.value = response.body()
                else error.value = "An error has occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}
package com.simon.popularmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.simon.popularmovies.R
import com.simon.popularmovies.model.MovieItem
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movie: MovieItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        initViews()
    }

    private fun initViews() {
        movie = intent.extras?.getParcelable(EXTRA_MOVIE)!!

        tvMovieTitle.text = movie.title
        tvMovieRating.text = movie.vote_average
        tvMovieRelease.text = movie.release_date
        tvMovieSummary.text = movie.overview

        Glide.with(this).load(movie.getPosterImage()).into(ivMoviePoster)
        Glide.with(this).load(movie.getBackdropImage()).into(ivMovieBackground)
    }
}

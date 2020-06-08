package com.simon.popularmovies.ui

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.simon.popularmovies.R
import com.simon.popularmovies.model.MovieItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Math.floor

const val EXTRA_MOVIE = "EXTRA_MOVIE"

class MainActivity : AppCompatActivity() {

    private val movies = mutableListOf<MovieItem>()
    private val movieAdapter = MovieAdapter(movies) {movieItem -> onMovieClick(movieItem) }

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViewModel()
        initViews()
        initListeners()
    }

    private fun initListeners() {
        btnSearch.setOnClickListener { findMovies() }
    }

    private fun initViews() {
        val asc = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvMovieList.layoutManager = asc
        rvMovieList.adapter = movieAdapter
    }

    private fun findMovies(){
        mainActivityViewModel.getMovies(etMovieYear.text.toString())
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.moviesList.observe(this, Observer {
            movies.clear()
            movies.addAll(it.results)
            movieAdapter.notifyDataSetChanged()
        })

        mainActivityViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun onMovieClick(movieItem: MovieItem) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_MOVIE, movieItem)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

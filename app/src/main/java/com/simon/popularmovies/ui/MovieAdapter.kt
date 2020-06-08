package com.simon.popularmovies.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simon.popularmovies.R
import com.simon.popularmovies.model.MovieItem
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: List<MovieItem>, private val onClick: (MovieItem) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movies[position], position+1)

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClick(movies[adapterPosition])
            }
        }

        fun bind(movie: MovieItem, position: Int) {
            itemView.tvNumber.text = "$position."
            Glide.with(context).load(movie.getPosterImage()).into(itemView.ivMovieFront)
        }
    }
}
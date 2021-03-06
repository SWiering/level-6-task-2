package com.simon.popularmovies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem (
    @SerializedName("backdrop_path")
    var backdrop_path: String,

    @SerializedName("poster_path")
    var poster_path: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("release_date")
    var release_date: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("vote_average")
    var vote_average: String
) : Parcelable {
    fun getBackdropImage(): String {
        return "https://image.tmdb.org/t/p/w500/$backdrop_path"
    }

    fun getPosterImage(): String {
        return "https://image.tmdb.org/t/p/w500/$poster_path"
    }
}
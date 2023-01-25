package com.training.mycompose

import java.io.Serializable

data class MovieDetails( val id: Int,
             val title: String,
             val description: String,
             val genres: String,
             val movieImage: Int = 0): Serializable {
}
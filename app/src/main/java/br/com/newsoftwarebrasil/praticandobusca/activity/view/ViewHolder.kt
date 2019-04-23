package br.com.newsoftwarebrasil.buscafilmes.activity.view

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.newsoftwarebrasil.buscafilmes.R

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvPopularMovie: CardView
    val title: TextView
    val imgPoster: ImageView
    var tvVoteAverage: TextView? = null
    var tvReleaseDate: TextView? = null
    val progress: ProgressBar

    init {
        cvPopularMovie = itemView.findViewById<View>(R.id.cv_popular_movie) as CardView
        title = itemView.findViewById<View>(R.id.tv_title) as TextView
        imgPoster = itemView.findViewById<View>(R.id.img_poster) as ImageView
        tvVoteAverage = itemView.findViewById(R.id.tv_vote_average) as TextView
        tvReleaseDate = itemView.findViewById(R.id.tv_release_date) as TextView
        progress = itemView.findViewById<View>(R.id.progress_bar) as ProgressBar
    }
}
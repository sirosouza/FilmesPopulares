package br.com.newsoftwarebrasil.buscafilmes.api.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
Pôster
Título
Gênero
Sinopse
Data de lançamento
*/

class PopularMovieResponse : Serializable {
    @SerializedName("vote_count")
    private val voteCount: Int = 0
    @SerializedName("id")
    private val id: Int = 0
    @SerializedName("title")
    private val title: String = ""
    @SerializedName("poster_path")
    private val posterPath: String = ""
    @SerializedName("vote_average")
    private val voteAverage: Double = 0.0
    @SerializedName("overview")
    private val overview: String = "";
    @SerializedName("release_date")
    private val releaseDate: String = ""
    /**
     *
    Nome do filme. ok
    Capa do filme. ok
    Avaliação do filme. ok
    Data de lançamento do filme. ok
    Autor. - não tem
    Direção - não tem
     */

    var getVoteCount: Int = 0
        get() = this.voteCount
    var getId: Int = 0
        get() = id
    var getTitle: String = ""
        get() = title
    var getPosterPath: String = ""
        get() = this.posterPath
    var getVoteAverage: Double = 0.0
        get() = this.voteAverage
    var getOverView: String = ""
        get() = this.overview
    var getReleaseDate: String = ""
        get() = this.releaseDate
}

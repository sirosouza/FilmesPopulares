package br.com.newsoftwarebrasil.praticandobusca.api.response

import br.com.newsoftwarebrasil.buscafilmes.api.response.PopularMovieResponse
import com.google.gson.annotations.SerializedName

class DataResponse {
    @SerializedName("page")
    private val page: Int? = 0
    @SerializedName("total_results")
    private val totalResults: Int? = 0
    @SerializedName("total_pages")
    private val totalPages: Int? = 0
    @SerializedName("results")
    private val list: ArrayList<PopularMovieResponse>? = null

    var getList: ArrayList<PopularMovieResponse>? = ArrayList()
        get() = this.list
}

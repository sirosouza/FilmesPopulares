package br.com.newsoftwarebrasil.buscafilmes.callback

import br.com.newsoftwarebrasil.praticandobusca.api.response.DataResponse
import retrofit2.Response

interface OnGetPopularMoviesListener {
    fun success(response: Response<DataResponse>)
    fun error()
}
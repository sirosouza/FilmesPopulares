package br.com.newsoftwarebrasil.praticandobusca.presenter

import br.com.newsoftwarebrasil.buscafilmes.api.PopularMovieRequester
import br.com.newsoftwarebrasil.buscafilmes.api.PopularMovieRequester.Companion.KEY
import br.com.newsoftwarebrasil.buscafilmes.callback.OnGetPopularMoviesListener
import br.com.newsoftwarebrasil.praticandobusca.api.response.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesPresenter {

    fun getPopularMovies(callback: OnGetPopularMoviesListener) {
        PopularMovieRequester.getServiceClient()?.getPopularMovies(KEY)
            ?.enqueue(object : Callback<DataResponse> {
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    t.printStackTrace()
                    callback.error()
                }

                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    callback.success(response)
                }
            })
    }
}
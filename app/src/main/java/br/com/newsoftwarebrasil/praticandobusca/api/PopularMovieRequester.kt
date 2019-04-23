package br.com.newsoftwarebrasil.buscafilmes.api

import br.com.newsoftwarebrasil.praticandobusca.api.response.DataResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class PopularMovieRequester {
    companion object {
        val KEY: String = "3fe6bfe1ce61712a0d76b3b834f2aa4f"
        private var popularMovieRequester: IPopularMovieRequester? = null

        private fun initApi() {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            popularMovieRequester = retrofit.create(IPopularMovieRequester::class.java)
        }


        fun getServiceClient(): IPopularMovieRequester? {
            if (popularMovieRequester == null) {
                initApi()
            }

            return popularMovieRequester
        }
    }

    interface IPopularMovieRequester {
        @GET("movie/popular")
        fun getPopularMovies(@Query("api_key") apiKey: String): Call<DataResponse>
    }
}

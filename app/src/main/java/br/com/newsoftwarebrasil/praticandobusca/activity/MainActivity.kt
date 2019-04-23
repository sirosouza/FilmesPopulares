package br.com.newsoftwarebrasil.buscafilmes.activity

import android.app.Activity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import br.com.newsoftwarebrasil.buscafilmes.R
import br.com.newsoftwarebrasil.buscafilmes.adapter.PopularMoviesAdapter
import br.com.newsoftwarebrasil.buscafilmes.callback.OnGetPopularMoviesListener
import br.com.newsoftwarebrasil.praticandobusca.api.response.DataResponse
import br.com.newsoftwarebrasil.praticandobusca.presenter.PopularMoviesPresenter
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var activity: Activity? = null
    private var popularMovieAdapter: PopularMoviesAdapter? = null
    private var popularMoviesPresenter: PopularMoviesPresenter? = null

    var root: ConstraintLayout? = null
    var rvPopularMovie: RecyclerView? = null
    var tvEmptyList: TextView? = null
    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        setContentView(R.layout.activity_main)
        activity = this

        root = findViewById(R.id.constraint)
        rvPopularMovie = findViewById(R.id.rv_tv_show)
        tvEmptyList = findViewById(R.id.tv_empty_list)
        progressBar = findViewById(R.id.progress_bar)

        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvPopularMovie?.layoutManager = layoutManager

        popularMoviesPresenter = PopularMoviesPresenter()


        progressBar?.visibility = View.VISIBLE
        popularMoviesPresenter?.getPopularMovies(object : OnGetPopularMoviesListener {
            override fun success(response: Response<DataResponse>) {
                if (response.code() == 200) {
                    var list: DataResponse? = response.body();

                    if (list?.getList?.size != 0) {
                        popularMovieAdapter = PopularMoviesAdapter(activity, list?.getList)
                        rvPopularMovie?.adapter = popularMovieAdapter
                        rvPopularMovie?.visibility = View.VISIBLE
                        tvEmptyList?.visibility = View.GONE
                    } else {
                        rvPopularMovie?.visibility = View.GONE
                        tvEmptyList?.visibility = View.VISIBLE
                    }
                } else {
                    Snackbar.make(root as View, getString(R.string.verify_connection), Snackbar.LENGTH_LONG).show()
                }
                progressBar?.visibility = View.GONE
            }

            override fun error() {
                progressBar?.visibility = View.GONE
            }

        })
    }
}
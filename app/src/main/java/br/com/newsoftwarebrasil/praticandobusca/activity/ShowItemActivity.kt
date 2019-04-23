package br.com.newsoftwarebrasil.buscafilmes.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.newsoftwarebrasil.buscafilmes.R
import br.com.newsoftwarebrasil.buscafilmes.adapter.PopularMoviesAdapter.Companion.SHOW_INTENT
import br.com.newsoftwarebrasil.buscafilmes.api.response.PopularMovieResponse
import br.com.newsoftwarebrasil.buscafilmes.util.PhotoUtil
import br.com.newsoftwarebrasil.praticandobusca.util.ConstantUtil.Companion.STORAGE
import br.com.newsoftwarebrasil.praticandobusca.util.CropSquareTransformationUtil
import br.com.newsoftwarebrasil.praticandobusca.util.DateUtil
import com.squareup.picasso.Callback
import java.util.*

class ShowItemActivity : AppCompatActivity() {
    private val TAG: String = ShowItemActivity::class.java.name
    private var context: Context? = null

    var toolbar: Toolbar? = null
    var title: TextView? = null
    var progress: ProgressBar? = null
    var imgPoster: ImageView? = null
    var tvVoteAverage: TextView? = null
    var tvOverView: TextView? = null
    var tvReleaseDate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContentView(R.layout.activity_show_item)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar);

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        title = findViewById(R.id.tv_title) as TextView
        progress = findViewById(R.id.progress_bar) as ProgressBar
        imgPoster = findViewById(R.id.img_poster) as ImageView
        tvVoteAverage = findViewById(R.id.tv_vote_average) as TextView
        tvOverView = findViewById(R.id.tv_overview) as TextView
        tvReleaseDate = findViewById(R.id.tv_release_date) as TextView

        if (intent.hasExtra(SHOW_INTENT)) {
            val popularMovie: PopularMovieResponse = intent.getSerializableExtra(SHOW_INTENT) as PopularMovieResponse

            title?.setText(popularMovie.getTitle)

            PhotoUtil.getInstance(
                context,
                toString().format(Locale.ENGLISH, popularMovie.getId),
                STORAGE + popularMovie.getPosterPath
            )
                ?.load(STORAGE + popularMovie.getPosterPath)
                ?.transform(CropSquareTransformationUtil())
                ?.into(imgPoster, object : Callback {
                    override fun onSuccess() {
                        Log.i(TAG, "Foto " + STORAGE + popularMovie.getPosterPath + " carregada com sucesso.")
                        imgPoster?.visibility = View.VISIBLE
                        progress?.visibility = View.GONE
                    }

                    override fun onError() {
                        Log.i(TAG, "Foto " + popularMovie.getPosterPath + " falhou.")
                        imgPoster?.visibility = View.VISIBLE
                        progress?.visibility = View.GONE
                    }
                })
            var voteAverage: String = popularMovie.getVoteAverage.toString()
            tvVoteAverage?.setText(voteAverage)
            tvOverView?.setText(
                popularMovie.getOverView
                    .replace("<p>", "")
                    .replace("</p>", "")
                    .replace("<b>", "")
                    .replace("</b>", "")
            )
            tvReleaseDate?.setText(DateUtil.getFormattedDate(popularMovie.getReleaseDate))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

package br.com.newsoftwarebrasil.buscafilmes.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.newsoftwarebrasil.buscafilmes.R
import br.com.newsoftwarebrasil.buscafilmes.activity.ShowItemActivity
import br.com.newsoftwarebrasil.buscafilmes.activity.view.ViewHolder
import br.com.newsoftwarebrasil.buscafilmes.api.response.PopularMovieResponse
import br.com.newsoftwarebrasil.buscafilmes.util.PhotoUtil
import br.com.newsoftwarebrasil.praticandobusca.util.ConstantUtil.Companion.STORAGE
import br.com.newsoftwarebrasil.praticandobusca.util.CropSquareTransformationUtil
import br.com.newsoftwarebrasil.praticandobusca.util.DateUtil
import com.squareup.picasso.Callback
import java.util.*

class PopularMoviesAdapter(
    private var context: Context?,
    private var popularMovieResponseList: ArrayList<PopularMovieResponse>?
) : RecyclerView.Adapter<ViewHolder>() {
    private val TAG: String = PopularMoviesAdapter::class.java.simpleName

    companion object {
        val SHOW_INTENT: String = "showIntent"
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.tv_show_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.setText(popularMovieResponseList?.get(position)?.getTitle)

        viewHolder.tvVoteAverage?.setText(popularMovieResponseList?.get(position)?.getVoteAverage.toString())

        PhotoUtil.getInstance(
            context,
            toString().format(Locale.ENGLISH, popularMovieResponseList?.get(position)?.getId),
            popularMovieResponseList?.get(position)?.getPosterPath
        )
            ?.load(STORAGE + popularMovieResponseList?.get(position)?.getPosterPath)
            ?.transform(CropSquareTransformationUtil())
            ?.into(viewHolder.imgPoster, object : Callback {
                override fun onSuccess() {
                    Log.i(
                        TAG,
                        "Foto " + STORAGE + popularMovieResponseList?.get(position)?.getPosterPath + " carregada com sucesso."
                    )
                    viewHolder.imgPoster.visibility = View.VISIBLE
                    viewHolder.progress.visibility = View.GONE
                }

                override fun onError() {
                    Log.i(
                        TAG,
                        "Foto " + popularMovieResponseList?.get(position)?.getPosterPath + " falhou."
                    )
                    viewHolder.imgPoster.visibility = View.VISIBLE
                    viewHolder.progress.visibility = View.GONE
                }
            })

        viewHolder.tvReleaseDate?.setText(DateUtil.getFormattedDate(popularMovieResponseList?.get(position)?.getReleaseDate))

        viewHolder.cvPopularMovie.setOnClickListener(View.OnClickListener {
            val intent: Intent? = Intent(context, ShowItemActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable(SHOW_INTENT, popularMovieResponseList?.get(position))
            intent?.putExtras(bundle)
            context?.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return popularMovieResponseList?.size!!
    }
}

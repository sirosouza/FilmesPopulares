package br.com.newsoftwarebrasil.buscafilmes.util

import android.content.Context
import android.util.Log
import br.com.newsoftwarebrasil.buscafilmes.BuildConfig
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.File

class PhotoUtil {

    companion object {
        private val TAG: String = PhotoUtil.javaClass.name
        private var picasso: Picasso? = null

        fun getInstance(context: Context?, id: String, url: String?): Picasso? {
            try {
                if (context != null) {

                    var fileName = ""

                    if (url != null) {
                        fileName = url + id + ".jpg"
                    }

                    val rawPath = context.cacheDir.absolutePath + "/" + "photo"

                    var file: File

                    val dir = File(context.cacheDir, rawPath)
                    if (!dir.exists()) {
                        dir.mkdir()
                        Log.i(TAG, "diretorio: $rawPath criado")
                        Log.i(TAG, "arquivo: $fileName criado")
                    }

                    file = File(dir, fileName)

                    if (!file.exists()) {
                        file = File(rawPath)
                        file.mkdirs()
                    }
                    val cache = Cache(file, (15 * 1024 * 1024).toLong())
                    val okHttpClientBuilder = OkHttpClient.Builder().cache(cache)
                    okHttpClientBuilder.networkInterceptors().add(Interceptor { chain ->
                        val originalResponse = chain.proceed(chain.request())
                        originalResponse.newBuilder().header("Cache-Control", "max-age=" + 60 * 60 * 24 * 365).build()
                    })

                    val okHttp3Downloader = OkHttp3Downloader(okHttpClientBuilder.build())

                    if (picasso == null)
                        picasso = Picasso.Builder(context)
                            .loggingEnabled(BuildConfig.DEBUG)
                            .indicatorsEnabled(BuildConfig.DEBUG)
                            .downloader(okHttp3Downloader)
                            .build()
                    return picasso
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            return Picasso.Builder(context)
                .build()
        }
    }
}

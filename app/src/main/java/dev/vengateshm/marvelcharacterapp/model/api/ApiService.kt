package dev.vengateshm.marvelcharacterapp.model.api

import dev.vengateshm.marvelcharacterapp.BuildConfig
import dev.vengateshm.marvelcharacterapp.getHash
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"

    private fun getRetrofit(): Retrofit {
        val timestamp = System.currentTimeMillis().toString()
        val apiKey = BuildConfig.MARVEL_KEY
        val apiSecret = BuildConfig.MARVEL_SECRET
        val hash = getHash(timestamp = timestamp, privateKey = apiSecret, publicKey = apiKey)

        val clientInterceptor = Interceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("ts", timestamp)
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("hash", hash)
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(clientInterceptor)
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        val client = clientBuilder.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api: MarvelApi by lazy {
        getRetrofit().create(MarvelApi::class.java)
    }
}
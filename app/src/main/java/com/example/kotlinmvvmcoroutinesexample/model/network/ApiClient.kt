package com.example.kotlinmvvmcoroutinesexample.model.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Lior Hazael
 */
object ApiClient {

    /**
     * The base url.
     */
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    /**
     * The [Moshi] builder.
     */
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    /**
     * The [Retrofit].
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}

/**
 * Defines how Retrofit talks to the service using the GET/POST/Etc method.
 */
interface ApiService {

    @GET("character")
    fun fetchCharacters(@Query("page") pair: String) : Call<CharacterResponse>

}
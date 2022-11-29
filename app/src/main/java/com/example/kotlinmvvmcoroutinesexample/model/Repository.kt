package com.example.kotlinmvvmcoroutinesexample.model

import com.example.kotlinmvvmcoroutinesexample.model.network.ApiService

/**
 * @author Lior Hazael
 */
class Repository(private val apiService: ApiService) {
    fun getCharacters(page: String) = apiService.fetchCharacters(page)
}
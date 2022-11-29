package com.example.kotlinmvvmcoroutinesexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmcoroutinesexample.model.Repository
import com.example.kotlinmvvmcoroutinesexample.model.network.ApiClient
import com.example.kotlinmvvmcoroutinesexample.model.network.Character
import com.example.kotlinmvvmcoroutinesexample.model.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response

/**
 * @author Lior Hazael
 */
class MainViewModel(
    private val repository: Repository = Repository(ApiClient.apiService)
) : ViewModel() {

    private var _charactersLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    val characterLiveData: LiveData<ScreenState<List<Character>?>>
        get() = _charactersLiveData

    init {
        fetchCharacter()
    }

    private fun fetchCharacter() {
        val client = repository.getCharacters("1")
        _charactersLiveData.postValue(Loading(null))
        client.enqueue(object : retrofit2.Callback<CharacterResponse> {

            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                if (response.isSuccessful)
                    _charactersLiveData.postValue(Success(response.body()?.result))
                else
                    _charactersLiveData.postValue(Error(response.code().toString(), null))

            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("Failure", "" + t.message)

                _charactersLiveData.postValue(Error(t.message, null))
            }

        })
    }
}
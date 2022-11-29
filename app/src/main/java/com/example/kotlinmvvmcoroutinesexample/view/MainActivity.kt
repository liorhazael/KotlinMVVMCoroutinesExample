package com.example.kotlinmvvmcoroutinesexample.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.kotlinmvvmcoroutinesexample.R
import com.example.kotlinmvvmcoroutinesexample.model.network.Character
import com.example.kotlinmvvmcoroutinesexample.viewmodel.Loading
import com.example.kotlinmvvmcoroutinesexample.viewmodel.MainViewModel
import com.example.kotlinmvvmcoroutinesexample.viewmodel.ScreenState
import com.example.kotlinmvvmcoroutinesexample.viewmodel.Success
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: MainAdapter

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.charactersRv)
        adapter = MainAdapter(mutableListOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        progressBar = findViewById(R.id.progress_bar)

        viewModel.characterLiveData.observe(this) { state ->
            processCharactersResponse(state)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun processCharactersResponse(state: ScreenState<List<Character>?>) {
        when (state) {
            is Loading -> {
               progressBar.visibility = View.VISIBLE
            }
            is Success -> {
                progressBar.visibility = View.GONE
                if (state.data != null) {
                    adapter.charactersList.clear()
                    adapter.charactersList.addAll(state.data)
                    adapter.notifyDataSetChanged()
                }
            }
            else -> {
                progressBar.visibility = View.GONE
                val view = progressBar.rootView
                Snackbar.make(view, state.message!!, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}
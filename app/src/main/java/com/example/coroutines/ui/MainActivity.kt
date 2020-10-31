package com.example.coroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutines.R
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.viewmodel.GithubViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: GithubViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(viewModel)
        initializeObserver()
    }

    private fun initializeObserver() {
        viewModel.state.observe(this, { state ->
            when(state.lastEvent) {
                is Loading -> Log.d("Claudio - ", "Loading")
                is ListReceived -> Log.d("Claudio - ", "List Received")
                is ErrorReceived -> Log.d("Claudio - ", "Error Received")
            }
        })
    }
}
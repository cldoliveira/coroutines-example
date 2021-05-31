package com.example.coroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.R
import com.example.coroutines.model.GithubEvent.*
import com.example.coroutines.model.GithubState
import com.example.coroutines.network.User
import com.example.coroutines.viewmodel.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: GithubViewModel by viewModel()
    private val adapter: GithubAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(viewModel)
        initializeRecyclerView()
        initializeObserver()
    }

    private fun initializeObserver() {
        viewModel.state.observe(this, { state ->
            when(state.lastEvent) {
                is ShowLoading -> progressBar.visibility = state.progressVisibility
                is ListReceived -> updateList(state)
                is ErrorReceived -> showErrorMsg(state)
            }
        })
    }

    private fun initializeRecyclerView() {
        rvUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvUsers.itemAnimator = DefaultItemAnimator()
        rvUsers.adapter = adapter
    }

    private fun updateList(state: GithubState) {
        progressBar.visibility = state.progressVisibility
        adapter.updateUsers(state.listOfUser)
    }

    private fun showErrorMsg(state: GithubState) {
        progressBar.visibility = state.progressVisibility
        Toast.makeText(this, state.errorMsg, Toast.LENGTH_LONG).show()
    }
}
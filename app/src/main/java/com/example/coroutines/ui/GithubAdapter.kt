package com.example.coroutines.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.R
import com.example.coroutines.network.User

class GithubAdapter: RecyclerView.Adapter<GithubViewHolder>() {

    private var users = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): GithubViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return GithubViewHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        holder.bindView(users[position])
    }

    fun updateUsers(newUsers: List<User>?) {
        if (newUsers != null) {
            users = newUsers
            notifyDataSetChanged()
        }
    }
}
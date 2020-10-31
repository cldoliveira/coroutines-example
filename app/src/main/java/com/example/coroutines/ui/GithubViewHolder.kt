package com.example.coroutines.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.network.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

class GithubViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bindView(user: User) {
        view.userName.text = user.login
        Picasso.with(view.context).load(user.avatar_url).into(view.userImage)
    }
}
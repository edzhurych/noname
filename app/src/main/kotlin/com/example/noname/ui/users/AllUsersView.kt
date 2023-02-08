package com.example.noname.ui.users

import android.content.Context
import com.example.domain.model.User
import com.example.noname.ui.LoadingView

interface AllUsersView : LoadingView {

    fun getContext(): Context?

    fun showMessage(message: String)
    fun showUsers(users: List<User>)
    fun showError()
}
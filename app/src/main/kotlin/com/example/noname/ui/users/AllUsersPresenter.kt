package com.example.noname.ui.users

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.domain.FailureGetUsers
import com.example.domain.GetUsersResult
import com.example.domain.SuccessGetUsers
import com.example.domain.model.User
import com.example.domain.usecase.SaveUserUseCase
import com.example.domain.usecase.GetUsersUseCase
import com.example.noname.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AllUsersPresenter(
    private val lifecycleOwner: LifecycleOwner,
    private val allUsersView: AllUsersView
): KoinComponent {

    private val saveUserUseCase: SaveUserUseCase by inject()
    private val getUsers: GetUsersUseCase by inject()

    fun init() {
        allUsersView.showProgress()

        lifecycleOwner.lifecycleScope.launch {
            val usersLIst: GetUsersResult = withContext(Dispatchers.IO) {
                getUsers()
            }
            when (usersLIst) {
                is SuccessGetUsers -> allUsersView.showUsers(usersLIst.value)
                is FailureGetUsers -> allUsersView.showError()
            }

            allUsersView.hideProgress()
        }
    }

    fun saveUser(user: User) {
        lifecycleOwner.lifecycleScope.launch {
            when (saveUserUseCase(user)) {
                -1L -> {
                    allUsersView.getContext()?.let {
                        allUsersView.showMessage(it.getString(R.string.user_is_not_saved))
                    }
                }
                else -> {
                    allUsersView.getContext()?.let {
                        allUsersView.showMessage(it.getString(R.string.user_is_saved))
                    }
                }
            }
        }
    }
}
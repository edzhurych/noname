package com.example.data.repository

import android.util.Log
import com.example.data.database.UsersDao
import com.example.data.database.mapToUser
import com.example.data.database.mapToUserEntry
import com.example.domain.FailureGetUsers
import com.example.domain.GetUsersResult
import com.example.domain.SuccessGetUsers
import com.example.data.network.NetworkApi
import com.example.domain.model.User
import com.example.domain.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.Response

class UsersRepositoryImpl(
    private val networkApi: NetworkApi,
    private val usersDao: UsersDao
) : UsersRepository {

    override suspend fun getUsers(): GetUsersResult =
        try {
            val response: Response<List<User>> = withContext(Dispatchers.IO) {
                networkApi.getUsers()
            }
            if (response.isSuccessful) {
                SuccessGetUsers(response.body() ?: emptyList())
            } else FailureGetUsers
        } catch (e: RuntimeException) {
            Log.e(this::class.simpleName, "Runtime Exception - $e")
            FailureGetUsers
        }

    override fun getSavedUsers(): Flow<List<User>> =
        try {
            usersDao.getUsers().map { it.mapToUser() }
        } catch(e: Exception) {
            emptyFlow()
        }

    override suspend fun addUser(user: User) =
        usersDao.addUser(user.mapToUserEntry())

    override suspend fun deleteUser(user: User) =
        usersDao.deleteUser(user.mapToUserEntry())
}
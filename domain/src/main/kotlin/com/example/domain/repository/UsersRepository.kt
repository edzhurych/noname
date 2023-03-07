package com.example.domain.repository

import com.example.domain.GetUsersResult
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(): GetUsersResult
    fun getSavedUsers(): Flow<List<User>>
    suspend fun getUserIds(): List<Long>
    suspend fun saveUser(user: User): Long
    suspend fun deleteUser(user: User)
}
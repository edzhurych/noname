package com.example.domain.usecase

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetSavedUsersUseCase {

    fun getSavedUsers(): Flow<List<User>>
    suspend fun getUserIds(): List<Long>
}
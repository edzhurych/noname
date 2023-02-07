package com.example.domain.usecase

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetSavedUsersUseCase {

    operator fun invoke(): Flow<List<User>>
}
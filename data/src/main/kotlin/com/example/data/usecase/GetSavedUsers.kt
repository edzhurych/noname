package com.example.data.usecase

import com.example.domain.model.User
import com.example.domain.repository.UsersRepository
import com.example.domain.usecase.GetSavedUsersUseCase
import kotlinx.coroutines.flow.Flow

class GetSavedUsers(private val repository: UsersRepository) : GetSavedUsersUseCase {
    override fun invoke(): Flow<List<User>> = repository.getSavedUsers()
}
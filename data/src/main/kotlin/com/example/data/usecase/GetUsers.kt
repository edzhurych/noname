package com.example.data.usecase

import com.example.domain.GetUsersResult
import com.example.domain.repository.UsersRepository
import com.example.domain.usecase.GetUsersUseCase

class GetUsers(private val repository: UsersRepository) : GetUsersUseCase {

    override suspend fun invoke(): GetUsersResult = repository.getUsers()
}
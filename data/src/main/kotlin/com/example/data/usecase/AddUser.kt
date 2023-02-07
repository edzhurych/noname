package com.example.data.usecase

import com.example.domain.model.User
import com.example.domain.repository.UsersRepository
import com.example.domain.usecase.AddUserUseCase

class AddUser(private val repository: UsersRepository) : AddUserUseCase {
    override suspend fun invoke(user: User) =
        repository.addUser(user)
}
package com.example.data.usecase

import com.example.domain.model.User
import com.example.domain.repository.UsersRepository
import com.example.domain.usecase.DeleteUserUseCase

class DeleteUser(private val repository: UsersRepository) : DeleteUserUseCase {
    override suspend fun invoke(user: User) =
        repository.deleteUser(user)
}
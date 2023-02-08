package com.example.data.usecase

import com.example.domain.model.User
import com.example.domain.repository.UsersRepository
import com.example.domain.usecase.SaveUserUseCase

class SaveUser(private val repository: UsersRepository) : SaveUserUseCase {
    override suspend fun invoke(user: User): Long =
        repository.saveUser(user)
}

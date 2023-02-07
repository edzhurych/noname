package com.example.domain.usecase

import com.example.domain.model.User

interface DeleteUserUseCase {

    suspend operator fun invoke(user: User)
}
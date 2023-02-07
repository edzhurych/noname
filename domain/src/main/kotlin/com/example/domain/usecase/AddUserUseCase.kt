package com.example.domain.usecase

import com.example.domain.model.User

interface AddUserUseCase {

    suspend operator fun invoke(user: User)
}
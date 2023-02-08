package com.example.domain.usecase

import com.example.domain.model.User

interface SaveUserUseCase {

    suspend operator fun invoke(user: User): Long
}
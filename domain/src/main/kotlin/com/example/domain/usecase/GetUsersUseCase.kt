package com.example.domain.usecase

import com.example.domain.GetUsersResult

interface GetUsersUseCase {

    suspend operator fun invoke(): GetUsersResult
}
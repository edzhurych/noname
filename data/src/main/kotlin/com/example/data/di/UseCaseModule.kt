package com.example.data.di

import com.example.data.usecase.SaveUser
import com.example.data.usecase.DeleteUser
import com.example.data.usecase.GetSavedUsers
import com.example.data.usecase.GetUsers
import com.example.domain.usecase.SaveUserUseCase
import com.example.domain.usecase.DeleteUserUseCase
import com.example.domain.usecase.GetSavedUsersUseCase
import com.example.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetUsersUseCase> {
        GetUsers(get())
    }

    factory<GetSavedUsersUseCase> {
        GetSavedUsers(get())
    }

    factory<SaveUserUseCase> {
        SaveUser(get())
    }

    factory<DeleteUserUseCase> {
        DeleteUser(get())
    }
}
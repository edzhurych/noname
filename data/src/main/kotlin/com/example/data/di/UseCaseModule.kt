package com.example.data.di

import com.example.data.usecase.AddUser
import com.example.data.usecase.DeleteUser
import com.example.data.usecase.GetSavedUsers
import com.example.data.usecase.GetUsers
import com.example.domain.usecase.AddUserUseCase
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

    factory<AddUserUseCase> {
        AddUser(get())
    }

    factory<DeleteUserUseCase> {
        DeleteUser(get())
    }
}
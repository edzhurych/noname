package com.example.data.di

import com.example.data.BASE_URL
import com.example.data.network.NetworkApi
import com.example.data.repository.UsersRepositoryImpl
import com.example.domain.repository.UsersRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val networkModule = module {

    single<NetworkApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    factory<UsersRepository> {
        UsersRepositoryImpl(get(), get())
    }
}

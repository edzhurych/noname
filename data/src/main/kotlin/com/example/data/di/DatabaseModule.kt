package com.example.data.di

import androidx.room.Room
import com.example.data.database.AppDatabase
import com.example.data.database.UsersDao
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(get(), AppDatabase::class.java, "database")
            .build()
    }

    single<UsersDao> {
        get<AppDatabase>().usersDao()
    }
}
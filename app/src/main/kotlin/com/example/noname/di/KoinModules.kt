package com.example.noname.di

import com.example.data.di.databaseModule
import com.example.data.di.networkModule
import com.example.data.di.useCaseModule

val koinModules = listOf(
    networkModule,
    useCaseModule,
    databaseModule
)
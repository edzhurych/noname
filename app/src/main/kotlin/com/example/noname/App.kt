package com.example.noname

import android.app.Application
import com.example.noname.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()

            androidContext(this@App)
            modules(koinModules)
        }
    }
}
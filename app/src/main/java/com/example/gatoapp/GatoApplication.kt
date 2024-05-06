package com.example.gatoapp

import android.app.Application
import com.example.gatoapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GatoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@GatoApplication)
            // Load modules
            modules(appModule)
        }
    }
}
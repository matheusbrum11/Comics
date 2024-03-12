package com.example.comics

import android.app.Application
import com.example.comics.data.di.dataModule
import com.example.comics.ui.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ComicsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ComicsApplication)
            modules(dataModule + viewModels)
        }
    }
}
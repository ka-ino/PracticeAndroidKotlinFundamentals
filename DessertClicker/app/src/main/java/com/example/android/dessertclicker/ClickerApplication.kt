package com.example.android.dessertclicker

import android.app.Application
import timber.log.Timber

class ClickerApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Timberを初期化
        Timber.plant(Timber.DebugTree())
    }
}
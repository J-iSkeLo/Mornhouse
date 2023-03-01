package dev.jiskelo.netronic

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Netronic :Application() {

    companion object {
        lateinit var instance: Netronic
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
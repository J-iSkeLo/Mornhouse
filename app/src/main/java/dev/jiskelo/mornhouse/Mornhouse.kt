package dev.jiskelo.mornhouse

import android.app.Application
import dev.jiskelo.mornhouse.di.appModule
import dev.jiskelo.mornhouse.di.repositoryModule
import dev.jiskelo.mornhouse.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Mornhouse :Application() {

    companion object {
        lateinit var instance: Mornhouse
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@Mornhouse)
            modules(
                listOf(appModule, repositoryModule, viewModelModule)
            )
        }
    }
}
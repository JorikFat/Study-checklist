package ru.pavlig43.overview

import android.app.Application
import org.koin.core.context.startKoin
import ru.pavlig43.overview.di.appModule

class OverviewApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}
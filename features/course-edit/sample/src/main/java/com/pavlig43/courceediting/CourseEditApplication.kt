package com.pavlig43.courceediting

import android.app.Application
import com.pavlig43.courceediting.di.appModule
import org.koin.core.context.startKoin

class CourseEditApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}
package com.pavlig43.courceediting

import android.app.Application
import com.pavlig43.courceediting.di.courseEditModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(courseEditModule)
        }

    }

}
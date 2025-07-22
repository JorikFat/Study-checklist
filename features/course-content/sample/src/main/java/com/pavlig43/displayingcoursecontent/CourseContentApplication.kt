package com.pavlig43.displayingcoursecontent

import android.app.Application
import com.pavlig43.displayingcoursecontent.di.appModule
import org.koin.core.context.startKoin

class CourseContentApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}
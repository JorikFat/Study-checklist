package ru.pavlig43.courses_list_sample

import android.app.Application
import org.koin.core.context.startKoin
import ru.pavlig43.courses_list_sample.di.appModule

class CourseListApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}
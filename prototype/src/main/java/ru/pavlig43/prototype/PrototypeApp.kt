package ru.pavlig43.prototype

import android.app.Application
import com.example.courses.CourseInteractor
import com.example.courses.database.repository.CoursesRepository
import com.example.courses.database.repository.FakeCoursesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.pavlig43.prototype.screens.content.contentModule
import ru.pavlig43.prototype.screens.courses.coursesListModule
import ru.pavlig43.prototype.screens.edit.editModule

class PrototypeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PrototypeApp)
            modules(
                module {
                    single <CoursesRepository> { FakeCoursesRepository() }//todo: rewrite to singleOf
                    singleOf(::CourseInteractor)
                },
                coursesListModule,
                contentModule,
                editModule
            )
        }
    }
}
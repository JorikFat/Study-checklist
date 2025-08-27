package ru.pavlig43.prototype

import android.app.Application
import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import com.example.courses.models.Lesson
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.pavlig43.prototype.di.prototypeModule
import ru.pavlig43.prototype.screens.courses.coursesListModule

class PrototypeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PrototypeApp)
            modules(
                prototypeModule,
                module {
                    single { CourseInteractor(stubCourses) }
                },
                coursesListModule,
            )
        }
    }
}

private val stubLessons: List<Lesson> = listOf(
    Lesson(0, "SRP"),
    Lesson(1, "OCP"),
    Lesson(2, "LSP"),
    Lesson(3, "ISP"),
    Lesson(4, "DIP"),
)

private val stubCourses = listOf(
    Course(0,"SOLID", stubLessons),
    Course(1,"Clean Architecture", listOf(Lesson(0,"Clean lesson"))),
    Course(2,"Design Patterns", listOf(Lesson(0,"DP lesson")))
)
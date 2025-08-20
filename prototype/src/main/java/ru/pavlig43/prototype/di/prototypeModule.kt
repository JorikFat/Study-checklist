package ru.pavlig43.prototype.di

import android.app.Application
import androidx.room.Room
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.Course
import ru.pavlig.course_edit.ui.CourseEditingViewModel
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel
import ru.pavlig43.prototype.database.AppDatabase
import ru.pavlig43.prototype.database.repository.CoursesRepositoryImpl

internal val prototypeModule = module {

    viewModel { CoursesViewModel() }
    viewModel { DisplayingCourseContentViewModel() }
    viewModel { (course: Course) -> CourseEditingViewModel(course) }

    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = "app_db"
        ).build()
    }

    single { CoursesRepositoryImpl(get()) }

}
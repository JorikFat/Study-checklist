package dev.jorik.study_checklist.di

import com.example.courses.CourseInteractor
import com.example.courses.database.AppDatabase
import ru.pavlig.course_edit.logic.CourseDraftEditor
import ru.pavlig.course_edit.logic.CourseEditInteractor
import com.example.courses.models.Course
import com.example.courses.repository.CoursesRepository
import com.example.courses.repository.CoursesRepositoryImpl
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.pavlig.course_edit.CourseEditingViewModel
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

internal val prototypeModule = module {
    single<AppDatabase> { AppDatabase.createAppDataBase(androidContext()) }
    factory<CoursesRepository> { CoursesRepositoryImpl(db = get()) }
    single { CourseInteractor(coursesRepository = get()) }
    factory<CourseDraftEditor> { (courseId: Int) ->
        val course: Course? = get<CourseInteractor>().findCourseById(courseId)
        CourseDraftEditor(course)
    }
    factory { (courseId: Int) ->
        CourseEditInteractor(
            get<CourseDraftEditor> { parametersOf(courseId) },
            get<CoursesRepository>()
        )
    }
    viewModel { CoursesViewModel(get()) }
    viewModel { (courseId: Int) -> DisplayingCourseContentViewModel(courseId, get()) }
    viewModel { (courseId: Int) ->
        CourseEditingViewModel(
            get<CourseEditInteractor> { parametersOf(courseId) }
        )
    }

}
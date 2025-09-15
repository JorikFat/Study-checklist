package dev.jorik.study_checklist.di

import com.example.courses.CourseInteractor
import com.example.courses.database.AppDatabase
import com.example.courses.repository.CoursesRepository
import com.example.courses.repository.CoursesRepositoryImpl
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingViewModel
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

internal val prototypeModule = module {

    single<AppDatabase> { AppDatabase.createAppDataBase(get()) }
    factory<CoursesRepository> { CoursesRepositoryImpl(db = get()) }
    single { CourseInteractor(coursesRepository = get()) }
    viewModel { CoursesViewModel(get()) }
    viewModel { (courseId: Int) -> DisplayingCourseContentViewModel(courseId, get()) }
    viewModel { (courseId: Int) -> CourseEditingViewModel(courseId, get()) }

}

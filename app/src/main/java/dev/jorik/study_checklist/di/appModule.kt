package dev.jorik.study_checklist.di

import androidx.room.Room
import com.example.courses.CourseInteractor
import com.example.courses.database.AppDatabase
import com.example.courses.repository.CoursesRepository
import com.example.courses.repository.CoursesRepositoryImpl
import com.example.courses.models.Course
import com.example.courses.models.Lesson
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingViewModel
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

internal val prototypeModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = "app_db"
        ).build()
    }
    factory<CoursesRepository> { CoursesRepositoryImpl(db = get()) }
    single { CourseInteractor(coursesRepository = get()) }
    viewModel { CoursesViewModel(get()) }
    viewModel { (courseId: Int) -> DisplayingCourseContentViewModel(courseId, get()) }
    viewModel { (courseId: Int) -> CourseEditingViewModel(courseId, get()) }

}

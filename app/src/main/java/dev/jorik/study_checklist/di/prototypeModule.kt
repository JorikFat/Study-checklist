package dev.jorik.study_checklist.di

import androidx.room.Room
import com.example.courses.CourseEditInteractor
import com.example.courses.CourseInteractor
import com.example.courses.database.AppDatabase
import com.example.courses.models.Course
import com.example.courses.models.Lesson
import com.example.courses.repository.CoursesRepository
import com.example.courses.repository.CoursesRepositoryImpl
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingViewModel
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

internal val prototypeModule = module {
    single<AppDatabase> { AppDatabase.createAppDataBase(androidContext()) }
    factory<CoursesRepository> { CoursesRepositoryImpl(db = get()) }
    single { CourseInteractor(coursesRepository = get()) }
    factory { (courseId: Int) ->
        val course = get<CourseInteractor>().findCourseById(courseId) ?: Course()
        CourseEditInteractor(course, get<CoursesRepository>())
    }
    viewModel { CoursesViewModel(get()) }
    viewModel { (courseId: Int) -> DisplayingCourseContentViewModel(courseId, get()) }
    viewModel { (courseId: Int) ->
        CourseEditingViewModel(get<CourseEditInteractor> {
            parametersOf(courseId)
        })
    }

}
private val stubLessons: List<Lesson> = listOf(
    "SRP",
    "OCP",
    "LSP",
    "ISP",
    "DIP",
).mapIndexed { index, lesson -> Lesson(index, lesson) }
private val stubCourses = listOf(
    Course(0, "SOLID", stubLessons),
    Course(1, "Clean Architecture", listOf(Lesson(0, "Clean lesson"))),
    Course(2, "Design Patterns", listOf(Lesson(0, "DP lesson")))
)

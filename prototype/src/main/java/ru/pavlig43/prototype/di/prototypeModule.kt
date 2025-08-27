package ru.pavlig43.prototype.di

import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import com.example.courses.models.Lesson
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingViewModel
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

internal val prototypeModule = module {
    single { CourseInteractor(initCourses = stubCourses) }
    viewModel { CoursesViewModel(get()) }
    viewModel { (courseId: Int) -> DisplayingCourseContentViewModel(courseId, get()) }
    viewModel { (courseId: Int) -> CourseEditingViewModel(courseId, get()) }

}
private val stubLessons: List<Lesson> = listOf(
    "SRP",
    "OCP",
    "LSP",
    "ISP",
    "DIP",
).mapIndexed { index, lesson -> Lesson(index, lesson) }
private val stubCourses = listOf(
    Course(0,"SOLID", stubLessons),
    Course(1,"Clean Architecture", listOf(Lesson(0,"Clean lesson"))),
    Course(2,"Design Patterns", listOf(Lesson(0,"DP lesson")))
)
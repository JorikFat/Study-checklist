package ru.pavlig43.prototype.screens.edit

import com.example.courses.CourseEditInteractor
import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import com.example.courses.repository.CoursesRepository
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingViewModel

val editModule = module {
    factory { (courseId: Int) ->
        val course = get<CourseInteractor>().findCourseById(courseId) ?: Course()
        CourseEditInteractor(course, get<CoursesRepository>())
    }
    viewModel { (courseId: Int) ->
        CourseEditingViewModel(get<CourseEditInteractor> {
            parametersOf(courseId)
        })
    }
}
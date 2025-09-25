package ru.pavlig43.prototype.screens.edit

import com.example.courses.edit.CourseEditInteractor
import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import com.example.courses.repository.CoursesRepository
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.pavlig.course_edit.CourseEditingViewModel

val editModule = module {
    factory { (courseId: Int) ->
        val course :Course? = get<CourseInteractor>().findCourseById(courseId)
        CourseEditInteractor(course, get<CoursesRepository>())
    }
    viewModel { (courseId: Int) ->
        CourseEditingViewModel(get<CourseEditInteractor> {
            parametersOf(courseId)
        })
    }
}
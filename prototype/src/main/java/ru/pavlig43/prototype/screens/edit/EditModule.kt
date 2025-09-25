package ru.pavlig43.prototype.screens.edit

import ru.pavlig.course_edit.logic.CourseEditInteractor
import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import com.example.courses.repository.CoursesRepository
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.pavlig.course_edit.CourseEditingViewModel
import ru.pavlig.course_edit.logic.CourseDraftEditor

val editModule = module {
    factory<CourseDraftEditor> { (courseId :Int) ->
        val course :Course? = get<CourseInteractor>().findCourseById(courseId)
        CourseDraftEditor(course)
    }
    factory { (courseId: Int) ->
        CourseEditInteractor(
            get<CourseDraftEditor> { parametersOf(courseId) },
            get<CoursesRepository>()
        )
    }
    viewModel { (courseId: Int) ->
        CourseEditingViewModel(get<CourseEditInteractor> {
            parametersOf(courseId)
        })
    }
}
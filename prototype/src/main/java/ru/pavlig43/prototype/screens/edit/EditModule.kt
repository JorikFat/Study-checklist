package ru.pavlig43.prototype.screens.edit

import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import com.example.courses.repository.CoursesRepository
import com.example.courses.repository.FakeCoursesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.pavlig.course_edit.CourseEditingViewModel
import ru.pavlig.course_edit.logic.CourseDraftEditor
import ru.pavlig.course_edit.logic.CourseEditInteractor

val editModule = module {
    singleOf(::FakeCoursesRepository) bind (CoursesRepository::class)
    singleOf(::CourseInteractor)
    factory<CourseDraftEditor> { (id: Int) ->
        val course: Course? = get<CourseInteractor>().findCourseById(id)
        CourseDraftEditor(course)
    }
    factory<CourseEditInteractor> { (id: Int) ->
        CourseEditInteractor(
            get<CourseDraftEditor> { parametersOf(id) },
            get<CoursesRepository>(),
        )
    }
    viewModel<CourseEditingViewModel> { (id: Int) ->
        CourseEditingViewModel(get<CourseEditInteractor> { parametersOf(id) })
    }
}
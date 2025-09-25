package ru.pavlig43.prototype.screens.edit

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.CourseEditingViewModel
import ru.pavlig.course_edit.logic.CourseDraftEditor
import ru.pavlig.course_edit.logic.CourseEditInteractor

val editModule = module {
    factoryOf(::CourseDraftEditor)
    factoryOf(::CourseEditInteractor)
    viewModel { (courseId: Int) ->
        CourseEditingViewModel(
            courseId = courseId,
            get<CourseEditInteractor>()
        )
    }
}
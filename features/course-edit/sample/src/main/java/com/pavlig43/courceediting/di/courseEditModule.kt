package com.pavlig43.courceediting.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.Course
import ru.pavlig.course_edit.ui.CourseEditingViewModel

val courseEditModule = module {
    viewModel { (course: Course) ->
        CourseEditingViewModel(
            course
        )
    }
}
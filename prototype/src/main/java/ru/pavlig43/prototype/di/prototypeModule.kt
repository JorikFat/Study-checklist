package ru.pavlig43.prototype.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingViewModel

internal val prototypeModule = module {
    viewModel { (courseId: Int) -> CourseEditingViewModel(courseId, get()) }
}

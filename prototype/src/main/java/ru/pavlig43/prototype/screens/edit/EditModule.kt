package ru.pavlig43.prototype.screens.edit

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingViewModel

val editModule = module {
    viewModel { (courseId: Int) -> CourseEditingViewModel(courseId, get()) }
}
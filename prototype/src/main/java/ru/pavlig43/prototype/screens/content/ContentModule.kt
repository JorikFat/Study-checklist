package ru.pavlig43.prototype.screens.content

import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val contentModule = module {
    viewModel { (courseId: Int) -> DisplayingCourseContentViewModel(courseId, get()) }
}
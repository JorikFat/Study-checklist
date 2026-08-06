package ru.pavlig43.prototype.screens.lessons

import dev.jorik.study_checklist.course_lessons.ui.CourseLessonsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val contentModule = module {
    viewModel { (courseId: Int) -> CourseLessonsViewModel(courseId, get()) }
}
package dev.jorik.study_checklist.course_content.di

import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun courseContentModuleFactory() = module {
    viewModelOf(::DisplayingCourseContentViewModel)
}
package ru.pavlig.course_edit.di


import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingViewModel

fun courseEditModuleFactory() = module {
    viewModelOf(::CourseEditingViewModel)
}
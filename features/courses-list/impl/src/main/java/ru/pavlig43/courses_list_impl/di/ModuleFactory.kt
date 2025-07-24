package ru.pavlig43.courses_list_impl.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

fun courseListModuleFactory() = module {
    viewModelOf(::CoursesViewModel)
}
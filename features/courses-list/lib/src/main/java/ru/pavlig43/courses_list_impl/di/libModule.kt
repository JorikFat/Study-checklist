package ru.pavlig43.courses_list_impl.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

val libModule = module {
    viewModel { CoursesViewModel() }
}
package ru.pavlig43.prototype.screens.courses

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

val coursesListModule = module {
    viewModelOf(::CoursesViewModel)
}
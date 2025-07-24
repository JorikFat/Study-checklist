package ru.pavlig43.courses_list_sample.di

import org.koin.dsl.module
import ru.pavlig43.courses_list_impl.di.courseListModuleFactory

internal val appModule = module {
    includes(courseListModuleFactory())
}
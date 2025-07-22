package com.pavlig43.courceediting.di

import org.koin.dsl.module
import ru.pavlig.course_edit.di.courseEditModuleFactory

internal val appModule = module {
    includes(courseEditModuleFactory())
}
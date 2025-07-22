package com.pavlig43.displayingcoursecontent.di

import dev.jorik.study_checklist.course_content.di.courseContentModuleFactory
import org.koin.dsl.module

val appModule = module {
    includes(courseContentModuleFactory())

}
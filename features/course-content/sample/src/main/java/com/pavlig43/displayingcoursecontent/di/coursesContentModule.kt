package com.pavlig43.displayingcoursecontent.di

import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val coursesContentModule = module {
    viewModel { DisplayingCourseContentViewModel() }
}
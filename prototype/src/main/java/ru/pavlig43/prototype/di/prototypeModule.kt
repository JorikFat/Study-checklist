package ru.pavlig43.prototype.di

import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.Course
import ru.pavlig.course_edit.ui.CourseEditingViewModel
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

internal val prototypeModule = module {

    viewModel { CoursesViewModel() }
    viewModel { DisplayingCourseContentViewModel() }
    viewModel { (course: Course) -> CourseEditingViewModel(course) }

}
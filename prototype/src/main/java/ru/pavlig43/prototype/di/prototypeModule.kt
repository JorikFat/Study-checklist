package ru.pavlig43.prototype.di

import com.example.courses.CourseInteractor
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.pavlig.course_edit.ui.CourseEditingViewModel
import ru.pavlig43.courses_list_impl.ui.CoursesViewModel

internal val prototypeModule = module {
    single{CourseInteractor()}
    viewModel { CoursesViewModel(get()) }
    viewModel { (courseId:Int) ->DisplayingCourseContentViewModel(courseId,get()) }
    viewModel { (courseId: Int) -> CourseEditingViewModel(courseId,get()) }

}
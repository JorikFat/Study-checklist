package ru.pavlig43.courses_list_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.pavlig43.courses_list_impl.data.CourseItemViewState

class CoursesViewModel(
    courseInteractor: CourseInteractor = CourseInteractor()
) : ViewModel() {
    val courses = courseInteractor.courseMenuList.map { lst ->
        lst.map { it.toViewState() }.sortedBy { it.id }
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList()
    )

}

private fun Course.toViewState(): CourseItemViewState {
    return CourseItemViewState(
        id = id,
        displayName = displayName
    )
}


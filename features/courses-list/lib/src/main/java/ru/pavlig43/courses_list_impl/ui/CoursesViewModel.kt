package ru.pavlig43.courses_list_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.CourseInteractor
import com.example.courses.data.CourseMenuItemData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.pavlig43.courses_list_impl.data.Course

class CoursesViewModel : ViewModel() {
    val courses = CourseInteractor.courseMenuList.map { lst ->
        lst.map { it.mapToCourse() }.sortedBy { it.id }
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList()
    )

}

private fun CourseMenuItemData.mapToCourse(): Course {
    return Course(
        id = id,
        displayName = displayName
    )
}


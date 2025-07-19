package ru.pavlig43.courses_list_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import ru.pavlig43.courses_list_impl.data.Course

class CoursesViewModel : ViewModel() {

    val courses = flow {
        emit(
            courseList
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList()
    )

}
internal val courseList = listOf("SOLID", "Clean Architecture", "Design Patterns").map { Course(it) }
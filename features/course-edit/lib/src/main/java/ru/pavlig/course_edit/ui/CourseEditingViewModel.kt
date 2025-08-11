package ru.pavlig.course_edit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CourseEditingViewModel(
    private val id: Int,
) : ViewModel() {

    private val _courseState = MutableStateFlow(Course())
    val courseState = _courseState.map { it.toViewState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            CourseDraftViewState()
        )

    fun onChangeCourseName(name: String) {
        _courseState.update { it.copy(displayName = name) }
    }

    init {
        getInitCourse()
    }

    private fun getInitCourse() {
        if (id == -1) return
        _courseState.update { CourseInteractor.getCourseById(id) }
    }

    fun onChangeLessonName(index: Int, value: String) {
        _courseState.update { course ->
            val updateLesson = course.lessons.toMutableList()
            val lesson = updateLesson.removeAt(index).copy(name = value)
            updateLesson.add(index,lesson)
            course.copy(
                lessons = updateLesson
            )
        }
    }

    fun onSave() {
        if (id == -1) {
            CourseInteractor.createCourse(_courseState.value)
        } else {
            CourseInteractor.updateCourse(_courseState.value)
        }
    }

}

private fun Course.toViewState(): CourseDraftViewState {
    return CourseDraftViewState(
        id = id,
        name = displayName,
        lessons = lessons.map { it.name }
    )
}

data class CourseDraftViewState(
    val id: Int = 0,
    val name: String = "",
    val lessons: List<String> = emptyList()

)


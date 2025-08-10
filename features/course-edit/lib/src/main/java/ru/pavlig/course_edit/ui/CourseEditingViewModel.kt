package ru.pavlig.course_edit.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CourseEditingViewModel(
    course: Course
) : ViewModel() {

    private val _courseState = MutableStateFlow(course)
    val courseState = _courseState.asStateFlow()

    fun onChangeCourseName(name: String) {
        _courseState.update { it.copy(name = name) }
    }

    fun onChangeLessonName(index: Int, value: String) {
        _courseState.update { course ->
            course.copy(
                lessons = course.lessons.map { lesson ->
                    if (lesson.index == index) {
                        lesson.copy(name = value)
                    } else {
                        lesson
                    }
                }
            )
        }
    }
    fun onSave(){

    }

}


data class Course(
    val name: String = "",
    val lessons: List<Lesson> = listOf(
        Lesson(0,"")
    )
)

data class Lesson(
    val index: Int,
    val name: String = ""
)


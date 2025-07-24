package dev.jorik.study_checklist.course_content.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class DisplayingCourseContentViewModel:ViewModel() {
    private val _courseState = MutableStateFlow(Course())
    val courseState = _courseState.asStateFlow()


    fun onCheckedChange(index: Int, isChecked: Boolean) {
        _courseState.update { course ->
            course.copy(
                lessons = course.lessons.map { lesson ->
                    if (lesson.index == index) {
                        lesson.copy(isChecked = isChecked)
                    } else {
                        lesson
                    }
                }
            )
        }
    }

}

data class Course(
    val name: String = "SOLID",
    val lessons: List<Lesson> = listOf(
        "SRP",
        "OCP",
        "LSP",
        "ISP",
        "DIP",
    ).mapIndexed { index, lesson -> Lesson(index, lesson) }
)

data class Lesson(
    val index: Int,
    val name: String,
    val isChecked:Boolean = false
)
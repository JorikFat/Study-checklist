package ru.pavlig.course_edit.ui

import androidx.lifecycle.ViewModel
import com.example.courses.CourseInteractor
import com.example.courses.data.CourseMenuItemData
import com.example.courses.data.LessonData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CourseEditingViewModel(
    private val id: Int,
) : ViewModel() {

    private val _courseState = MutableStateFlow(Course())
    val courseState = _courseState.asStateFlow()

    fun onChangeCourseName(name: String) {
        _courseState.update { it.copy(name = name) }
    }

    init {
        getInitCourse()
    }

    private fun getInitCourse() {
        if (id == 0) return
        _courseState.update { CourseInteractor.getCourseById(id).mapToCourseContent() }
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

    fun onSave() {
        if (id == 0) {
            CourseInteractor.createCourse(_courseState.value.mapToCourseMenuItemData())
        } else {
            CourseInteractor.updateCourse(_courseState.value.mapToCourseMenuItemData())
        }
    }

}

private fun CourseMenuItemData.mapToCourseContent(): Course {
    return Course(
        id = id,
        name = displayName,
        lessons = lessons.map { it.mapToLesson() }
    )
}

private fun LessonData.mapToLesson(): Lesson {
    return Lesson(
        index = id,
        name = name,
        isChecked = isChecked
    )
}

private fun Course.mapToCourseMenuItemData(): CourseMenuItemData {
    return CourseMenuItemData(
        id = id,
        displayName = name,
        lessons = lessons.map { it.toLessonData() }
    )
}

private fun Lesson.toLessonData(): LessonData {
    return LessonData(
        id = index,
        name = name,
        isChecked = isChecked
    )
}

data class Course(
    val id: Int = 0,
    val name: String = "",
    val lessons: List<Lesson> = listOf(
        Lesson(0, "")
    )
)

data class Lesson(
    val index: Int,
    val name: String = "",
    val isChecked: Boolean = false
)


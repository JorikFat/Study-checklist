package ru.pavlig.course_edit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.CourseEditInteractor
import com.example.courses.models.Course
import com.example.courses.models.Lesson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseEditingViewModel(
    private val courseEditInteractor: CourseEditInteractor
) : ViewModel() {

    private val _courseState = MutableStateFlow(
        courseEditInteractor.initialCourse.toViewState()
    )
    val courseState = _courseState.asStateFlow()

    fun onChangeCourseName(name: String) {
        _courseState.update { it.copy(name = name) }
    }

    fun onChangeLessonName(index: Int, value: String) {
        _courseState.update { course ->
            course.copy(
                lessons = course.lessons.mapIndexed { lessonIndex, lesson ->
                    if (lessonIndex == index) {
                        lesson.copy(name = value)
                    } else {
                        lesson
                    }
                }
            )
        }
    }

    fun onDeleteCourse() {
        viewModelScope.launch {
            courseEditInteractor.deleteCourse()
        }
    }

    fun onAddLesson() {
        _courseState.update { course ->
            course.copy(
                lessons = course.lessons.plus(
                    LessonDraftViewState()
                )
            )
        }
    }

    fun onDeleteLesson(index: Int) {
        _courseState.update { course ->
            course.copy(
                lessons = course.lessons.minus(
                    course.lessons[index]
                )
            )
        }
    }

    fun onSave() {
        viewModelScope.launch {
            courseEditInteractor.updateCourse(_courseState.value.toCourse())
        }
    }

}

private fun Course.toViewState(): CourseDraftViewState {
    return CourseDraftViewState(
        id = id,
        name = displayName,
        lessons = lessons.map { it.toViewState() }
    )
}

private fun Lesson.toViewState(): LessonDraftViewState {
    return LessonDraftViewState(
        id = id,
        name = name,
        isChecked = isChecked
    )
}

private fun CourseDraftViewState.toCourse(): Course {
    return Course(
        id = id,
        displayName = name,
        lessons = lessons.map { it.toLesson() }
    )
}

private fun LessonDraftViewState.toLesson(): Lesson {
    return Lesson(
        id = id,
        name = name,
        isChecked = isChecked
    )
}

data class CourseDraftViewState(
    val id: Int = 0,
    val name: String = "",
    val lessons: List<LessonDraftViewState> = listOf(LessonDraftViewState())

)

data class LessonDraftViewState(
    val id: Int = 0,
    val name: String = "",
    val isChecked: Boolean = false
)



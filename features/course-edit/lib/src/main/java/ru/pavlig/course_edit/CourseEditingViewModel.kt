package ru.pavlig.course_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.edit.CourseEditInteractor
import com.example.courses.edit.models.CourseDraft
import com.example.courses.edit.models.LessonDraft
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseEditingViewModel(
    private val interactor: CourseEditInteractor
) : ViewModel() {

    private val _courseState :MutableStateFlow<CourseDraft> = MutableStateFlow(
        interactor.initialCourse?.let(::CourseDraft) ?: CourseDraft()
    )
    val courseState = _courseState.asStateFlow()

    fun onChangeCourseName(name: String) {
        _courseState.update { it.copy(name = name) }
    }

    fun onChangeLessonName(index :Int, name: String) {
        _courseState.update { course ->
            course.copy(
                lessons = course.lessons.toMutableList()
                    .also { it[index] = it[index].copy(name = name) }
            )
        }
    }

    fun onDeleteCourse() {
        viewModelScope.launch {
            interactor.deleteCourse()
        }
    }

    fun onAddLesson() {
        _courseState.update { course ->
            course.copy(
                lessons = course.lessons.plus(LessonDraft())
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
            interactor.updateCourse(_courseState.value)
        }
    }

}
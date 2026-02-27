package ru.pavlig.course_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.pavlig.course_edit.logic.CourseEditInteractor
import ru.pavlig.course_edit.logic.models.CourseDraft

sealed class CourseEditingEvents() {
    object NavigateBack : CourseEditingEvents()
    object NavigateContentScreen : CourseEditingEvents()
}

class CourseEditingViewModel(
    private val interactor: CourseEditInteractor
) : ViewModel() {
    val courseState :StateFlow<CourseDraft> = interactor.flow
    private val _eventsFlow = MutableSharedFlow<CourseEditingEvents>()
    val eventsFlow = _eventsFlow.asSharedFlow()

    fun onSave() {
        viewModelScope.launch {
            if (interactor.updateCourseSuccess()) {
                _eventsFlow.emit(CourseEditingEvents.NavigateContentScreen)
            }
        }
    }

    fun onDeleteCourse() {
        viewModelScope.launch {
            interactor.deleteCourse()
        }
    }

    fun onChangeCourseName(name: String) =
        interactor.changeCourseName(name)

    fun onChangeLessonName(index :Int, name: String) =
        interactor.changeLessonName(index, name)

    fun onAddLesson() =
        interactor.addLesson()

    fun onDeleteLesson(index: Int) =
        interactor.deleteLesson(index)

    fun onNavigateBack() {
        viewModelScope.launch {
            _eventsFlow.emit(CourseEditingEvents.NavigateBack)
        }
    }
}
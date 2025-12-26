package ru.pavlig.course_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import ru.pavlig.course_edit.logic.CourseEditInteractor
import kotlinx.coroutines.launch
import ru.pavlig.course_edit.logic.models.CourseDraft

class CourseEditingViewModel(
    private val interactor: CourseEditInteractor
) : ViewModel() {
    val courseState :StateFlow<CourseDraft> = interactor.flow

    fun onSave() {
        viewModelScope.launch {
            interactor.updateCourse()
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
}
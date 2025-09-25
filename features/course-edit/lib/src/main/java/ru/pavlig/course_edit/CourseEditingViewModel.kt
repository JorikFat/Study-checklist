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
    val courseState = interactor.flow

    fun onDeleteCourse() {
        viewModelScope.launch {
            interactor.deleteCourse()
        }
    }

    fun onSave() {
        viewModelScope.launch {
            interactor.updateCourse()
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
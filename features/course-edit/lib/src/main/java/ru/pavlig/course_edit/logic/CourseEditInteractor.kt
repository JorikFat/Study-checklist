package ru.pavlig.course_edit.logic

import com.example.courses.repository.CoursesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.pavlig.course_edit.logic.models.CourseDraft

class CourseEditInteractor(
    private val editor: CourseDraftEditor,
    private val repository: CoursesRepository
) {
    private val state = MutableStateFlow<CourseDraft>(editor.state)
    val flow = state.asStateFlow()

    suspend fun updateCourse() {
        if (editor.srcCourse == null) {
            repository.courseCreate(editor.course)
        } else {
            editor.srcCourse.lessons
                .filterNot { editor.course.lessons.contains(it) }
                .let { repository.courseUpdate(editor.course, it) }
        }
        state.update { editor.state }
    }

    suspend fun deleteCourse() =
        repository.courseDelete(editor.course)

    fun changeCourseName(name: String) {
        editor.changeCourseName(name)
        state.update { editor.state }
    }

    fun changeLessonName(index: Int, name: String) {
        editor.changeLessonName(index, name)
        state.update { editor.state }
    }

    fun addLesson() {
        editor.addLesson()
        state.update { editor.state }
    }

    fun deleteLesson(index: Int) {
        editor.deleteLesson(index)
        state.update { editor.state }
    }
}
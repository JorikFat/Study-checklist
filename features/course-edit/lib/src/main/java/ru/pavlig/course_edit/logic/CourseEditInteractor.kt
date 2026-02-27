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

    suspend fun updateCourseSuccess(): Boolean {
        if (!validateCourse()) return false

        if (editor.srcCourse == null) {
            repository.courseCreate(editor.course)
        } else {
            editor.srcCourse.lessons
                .filterNot { editor.course.lessons.contains(it) }
                .let { repository.courseUpdate(editor.course, it) }
        }
        state.update { editor.state }
        return true
    }

    private fun validateCourse(): Boolean {
        state.update { editor.state} // refresh error fields to default values

        var hasNoError = true
        if (editor.state.name.isBlank()) {
            hasNoError = false
            state.value = state.value.copy(hasCourseNameError = true)
        }
        val updatedLessons = editor.state.lessons.map { lesson ->
            if (lesson.name.isBlank()) {
                hasNoError = false
                lesson.copy(isError = true)
            } else {
                lesson.copy(isError = false)
            }
        }
        state.value = state.value.copy(
            lessons = updatedLessons
        )
        return hasNoError
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
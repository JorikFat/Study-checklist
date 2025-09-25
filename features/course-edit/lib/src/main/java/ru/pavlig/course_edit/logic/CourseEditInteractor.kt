package ru.pavlig.course_edit.logic

import com.example.courses.repository.CoursesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CourseEditInteractor(
    private val editor: CourseDraftEditor,
    private val repository: CoursesRepository
) {

    private val state = MutableStateFlow(editor.draft)
    val flow = state.asStateFlow()

    suspend fun updateCourse() {
        val newCourse = editor.course
        if (newCourse.id == 0) repository.courseCreate(newCourse)
        else newCourse.lessons
            .filterNot { newCourse.lessons.contains(it) }
            .let {
                repository.courseUpdate(newCourse, it)
            }
    }

    suspend fun deleteCourse() =
        repository.courseDelete(editor.course)

    fun changeCourseName(name :String){
        editor.changeCourseName(name)
        state.update { editor.draft }
    }

    fun changeLessonName(index :Int, name :String) {
        editor.changeLessonName(index, name)
        state.update { editor.draft }
    }

    fun addLesson() {
        editor.addLesson()
        state.update { editor.draft }
    }

    fun deleteLesson(index :Int) {
        editor.deleteLesson(index)
        state.update { editor.draft }
    }
}
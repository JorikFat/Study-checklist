package ru.pavlig.course_edit.logic

import com.example.courses.models.Course
import com.example.courses.repository.CoursesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CourseEditInteractor(
    private val editor: CourseDraftEditor,
    private val repository: CoursesRepository
) {
    private val state = MutableStateFlow<CourseEditState>(CourseEditState.Loading)
    val flow : StateFlow<CourseEditState> = state.asStateFlow()

    suspend fun init(courseId: Int) {
        val course: Course? = if (courseId == 0) null
        else repository.getCourse(courseId)
        editor.init(course)
        state.update { CourseEditState.Data(editor.draft) }
    }

    suspend fun updateCourse() {
        val newCourse = editor.course
        if (editor.srcCourse == null) {
            repository.courseCreate(newCourse)
        } else {
            editor.srcCourse!!.lessons
                .filterNot { newCourse.lessons.contains(it) }
                .let { repository.courseUpdate(newCourse, it) }
        }
        state.update { CourseEditState.Data(editor.draft) }
    }

    suspend fun deleteCourse() =
        repository.courseDelete(editor.course)

    fun changeCourseName(name: String) {
        editor.changeCourseName(name)
        state.update { CourseEditState.Data(editor.draft) }
    }

    fun changeLessonName(index: Int, name: String) {
        editor.changeLessonName(index, name)
        state.update { CourseEditState.Data(editor.draft) }
    }

    fun addLesson() {
        editor.addLesson()
        state.update { CourseEditState.Data(editor.draft) }
    }

    fun deleteLesson(index: Int) {
        editor.deleteLesson(index)
        state.update { CourseEditState.Data(editor.draft) }
    }
}
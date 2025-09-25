package com.example.courses.edit

import com.example.courses.edit.models.CourseDraft
import com.example.courses.repository.CoursesRepository
import kotlinx.coroutines.flow.StateFlow

class CourseEditInteractor(
    private val editor: CourseDraftEditor,
    private val coursesRepository: CoursesRepository
) {
    val flow : StateFlow<CourseDraft> = editor.flow

    suspend fun updateCourse() {
        val newCourse = editor.course
        if (newCourse.id == 0) coursesRepository.courseCreate(newCourse)
        else newCourse.lessons
            .filterNot { newCourse.lessons.contains(it) }
            .let {
                coursesRepository.courseUpdate(newCourse, it)
            }
    }

    suspend fun deleteCourse() =
        coursesRepository.courseDelete(editor.course)

    fun changeCourseName(name :String) =
        editor.changeCourseName(name)

    fun changeLessonName(index :Int, name :String) =
        editor.changeLessonName(index, name)

    fun addLesson() =
        editor.addLesson()

    fun deleteLesson(index :Int) =
        editor.deleteLesson(index)
}
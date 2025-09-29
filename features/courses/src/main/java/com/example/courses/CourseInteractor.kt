package com.example.courses

import com.example.courses.models.Course
import com.example.courses.repository.CoursesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CourseInteractor(
    private val coursesRepository: CoursesRepository,
) {
    private val _courseMenuList : StateFlow<List<Course>> = coursesRepository.listen().stateIn(
        GlobalScope,
        SharingStarted.Eagerly,
        initialValue = emptyList()
    )
    val courseMenuList :Flow <List<Course>> = _courseMenuList

    fun findCourseById(id: Int): Course? {
        return _courseMenuList.value.find { it.id == id }
    }

    suspend fun toggleLesson(courseId: Int, lessonId: Int) {
        findCourseById(courseId)!!.lessons
            .find { it.id == lessonId }
            ?.let { lesson ->
                coursesRepository.lessonUpdate(
                    courseId = courseId,
                    lesson = lesson.copy(isChecked = !lesson.isChecked)
                )
            }
    }
}
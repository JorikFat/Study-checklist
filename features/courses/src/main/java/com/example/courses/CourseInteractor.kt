package com.example.courses

import com.example.courses.repository.CoursesRepository
import com.example.courses.models.Course
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseInteractor(
    private val coursesRepository: CoursesRepository,
) {
    private val _courseMenuList = MutableStateFlow<List<Course>>(emptyList())
    val courseMenuList = _courseMenuList.asStateFlow()

    init {
        launchOnGlobalScope { initCourses() }
    }

    private suspend fun initCourses() {
        _courseMenuList.update {
            coursesRepository.getCourses()
        }
    }

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
        initCourses()
    }


    private fun launchOnGlobalScope(block: suspend () -> Unit) = GlobalScope.launch {
        block.invoke()
    }
}
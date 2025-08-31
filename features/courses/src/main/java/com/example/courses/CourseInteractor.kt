package com.example.courses

import com.example.courses.models.Course
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CourseInteractor(initCourses:List<Course> = emptyList()) {
    private val _courseMenuList = MutableStateFlow<List<Course>>(initCourses)
    val courseMenuList = _courseMenuList.asStateFlow()

    fun findCourseById(id: Int): Course? {
        return _courseMenuList.value.find { it.id == id }
    }


    fun createCourse(course: Course) {
        val lastIndex = _courseMenuList.value.maxOfOrNull { it.id }
        updateCourseList { it.add(course.copy(id = lastIndex?.plus(1) ?: 0)) }
    }

    fun updateCourse(course: Course) {
        updateCourseList { lst ->
            lst.removeIf { course.id == it.id }
            lst.add(course)
        }
    }
    fun deleteCourse(id: Int) {
        updateCourseList { it.removeIf { course -> course.id == id } }
    }
    fun toggleLesson(courseId: Int, lessonId: Int){
        val course = _courseMenuList.value.first { it.id == courseId}
        val updatedLessons = course.lessons.map { lesson ->
            if (lesson.id == lessonId) {
                lesson.copy(isChecked = !lesson.isChecked)
            } else {
                lesson
            }
        }
        val updatedCourse = course.copy(
            lessons = updatedLessons
        )
        updateCourse(updatedCourse)
    }



    private fun updateCourseList(action: (MutableList<Course>) -> Unit) {
        val updatedList = _courseMenuList.value.toMutableList()
        action(updatedList)
        _courseMenuList.update { updatedList }
    }
}
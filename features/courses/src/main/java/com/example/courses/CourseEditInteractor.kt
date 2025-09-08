package com.example.courses

import com.example.courses.models.Course
import com.example.courses.repository.CoursesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CourseEditInteractor(
    val initialCourse: Course,
    private val coursesRepository: CoursesRepository
) {

    suspend fun createCourse(course: Course) {
        coursesRepository.courseCreate(course)
    }

    suspend fun updateCourse(course: Course) {
        initialCourse.lessons.filterNot { course.lessons.contains(it) }.let {
            coursesRepository.courseUpdate(course, it)
        }
    }

    suspend fun deleteCourse() {
        coursesRepository.courseDelete(initialCourse)
    }

    suspend fun toggleLesson(lessonId: Int) {
        initialCourse.lessons
            .find { it.id == lessonId }
            ?.let { lesson ->
                coursesRepository.lessonUpdate(
                    courseId = initialCourse.id,
                    lesson = lesson.copy(isChecked = !lesson.isChecked)
                )
            }
    }

}
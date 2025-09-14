package com.example.courses

import com.example.courses.models.Course
import com.example.courses.repository.CoursesRepository

class CourseEditInteractor(
    val initialCourse: Course,
    private val coursesRepository: CoursesRepository
) {

    suspend fun updateCourse(course: Course) {
        if (initialCourse.id == 0) coursesRepository.courseCreate(course)
        else initialCourse.lessons
            .filterNot { course.lessons.contains(it) }
            .let {
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
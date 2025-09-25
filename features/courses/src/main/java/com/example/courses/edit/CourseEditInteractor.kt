package com.example.courses.edit

import com.example.courses.edit.models.CourseDraft
import com.example.courses.edit.models.LessonDraft
import com.example.courses.models.Course
import com.example.courses.models.Lesson
import com.example.courses.repository.CoursesRepository

class CourseEditInteractor(
    val initialCourse: Course? = null,
    private val coursesRepository: CoursesRepository
) {


    suspend fun updateCourse(draft: CourseDraft) {
        val newCourse = draft.course
        if (initialCourse == null) coursesRepository.courseCreate(newCourse)
        else initialCourse.lessons
            .filterNot { newCourse.lessons.contains(it) }
            .let {
                coursesRepository.courseUpdate(newCourse, it)
            }
    }

    suspend fun deleteCourse() {
        coursesRepository.courseDelete(initialCourse!!)
    }

    private val CourseDraft.course :Course get() = Course(
        id = id,
        displayName = name,
        lessons = lessons.map { it.lesson }
    )

    private val LessonDraft.lesson : Lesson get() = Lesson(
        id = id,
        name = name,
        isChecked = isChecked
    )
}
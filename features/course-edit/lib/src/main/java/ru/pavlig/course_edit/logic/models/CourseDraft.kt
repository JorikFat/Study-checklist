package ru.pavlig.course_edit.logic.models

import com.example.courses.models.Course

data class CourseDraft(
    val id: Int = 0,
    val name :String = "",
    val lessons :List<LessonDraft> = listOf(LessonDraft())
) {
    constructor(course: Course) :this(
        id = course.id,
        name = course.displayName,
        lessons = course.lessons.map(::LessonDraft)
    )
}
package ru.pavlig.course_edit.logic.models

data class CourseDraft(
    val id: Int = 0,
    val name :String = "",
    val lessons :List<LessonDraft> = listOf(LessonDraft())
)
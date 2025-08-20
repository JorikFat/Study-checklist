package ru.pavlig43.prototype.models

data class CourseContentViewState(
    val course: CourseViewState,
    val lessons: List<LessonViewState>
)

package com.example.courses.models

data class Course(
    val id: Int = 0,
    val displayName: String,
    val lessons: List<Lesson>
) {
    companion object Stub {
        val courses = listOf(
            Course(1, "SOLID", Lesson.lessons),
            Course(2, "Clean Architecture", listOf(Lesson(6, "Clean lesson"))),
            Course(3, "Design Patterns", listOf(Lesson(7, "DP lesson")))
        )
    }
}

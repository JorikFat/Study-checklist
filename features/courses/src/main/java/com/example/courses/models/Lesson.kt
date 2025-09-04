package com.example.courses.models

data class Lesson(
    val id: Int,
    val name: String,
    val isChecked: Boolean = false
) {
    companion object Stub {
        val lessons: List<Lesson> = listOf(
            Lesson(1, "SRP", false),
            Lesson(2, "OCP", false),
            Lesson(3, "LSP", false),
            Lesson(4, "ISP", false),
            Lesson(5, "DIP", false)
        )
    }
}
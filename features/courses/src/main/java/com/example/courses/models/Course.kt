package com.example.courses.models

data class Course(
    val id:Int = 0,
    val displayName:String ="",
    val lessons:List<Lesson> = emptyList()
)
data class Lesson(
    val id: Int,
    val name: String,
    val isChecked:Boolean = false
)

private val stubLessons: List<Lesson> = listOf(
    "SRP",
    "OCP",
    "LSP",
    "ISP",
    "DIP",
).mapIndexed { index, lesson -> Lesson(index, lesson) }
internal val stubCourses = listOf(
    Course(0,"SOLID", stubLessons),
    Course(1,"Clean Architecture"),
    Course(2,"Design Patterns")
)

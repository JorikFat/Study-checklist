package com.example.courses.data

data class CourseMenuItemData(
    val id:Int = 0,
    val displayName:String ="",
    val lessons:List<LessonData> = emptyList()
)
data class LessonData(
    val id: Int,
    val name: String,
    val isChecked:Boolean = false
)

private val lessons: List<LessonData> = listOf(
    "SRP",
    "OCP",
    "LSP",
    "ISP",
    "DIP",
).mapIndexed { index, lesson -> LessonData(index, lesson) }
internal val courseList = listOf(
    CourseMenuItemData(1,"SOLID",lessons),
    CourseMenuItemData(2,"Clean Architecture"),
    CourseMenuItemData(3,"Design Patterns")
)

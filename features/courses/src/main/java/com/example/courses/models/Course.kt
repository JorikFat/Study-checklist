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



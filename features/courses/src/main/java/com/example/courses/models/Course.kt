package com.example.courses.models

data class Course(
    val id:Int = 0,
    val displayName:String ="",
    val lessons:List<Lesson> = emptyList()
) {
    companion object Stub {

        val courses = listOf(
            Course(0,"SOLID", Lesson.lessons),
            Course(1,"Clean Architecture", listOf(Lesson(0,"Clean lesson"))),
            Course(2,"Design Patterns", listOf(Lesson(0,"DP lesson")))
        )
    }
}



data class Lesson(
    val id: Int,
    val name: String,
    val isChecked:Boolean = false
) {
    companion object Stub {
        val lessons: List<Lesson> = listOf(
            "SRP",
            "OCP",
            "LSP",
            "ISP",
            "DIP",
        ).mapIndexed { index, lesson -> Lesson(index, lesson) }
    }
}



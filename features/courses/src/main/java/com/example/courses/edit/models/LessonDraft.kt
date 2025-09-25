package com.example.courses.edit.models

import com.example.courses.models.Lesson

data class LessonDraft(
    val id: Int = 0,
    val name: String = "",
    val isChecked: Boolean = false
) {
    constructor(lesson : Lesson) :this(
        id = lesson.id,
        name = lesson.name,
        isChecked = lesson.isChecked
    )
}

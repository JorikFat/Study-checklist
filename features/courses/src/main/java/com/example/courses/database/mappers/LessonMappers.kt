package com.example.courses.database.mappers

import com.example.courses.database.entities.LessonEntity
import com.example.courses.models.Lesson

fun LessonEntity.toLesson(): Lesson {
    return Lesson(
        id = this.index,
        name = this.name,
        isChecked = this.isChecked
    )
}

fun Lesson.toEntity(courseId: Int): LessonEntity {
    return LessonEntity(
        index = this.id,
        courseId = courseId,
        name = this.name,
        isChecked = this.isChecked
    )
}
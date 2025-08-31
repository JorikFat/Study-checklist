package com.example.courses.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CourseContentEntity(
    @Embedded val course: CourseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "courseId"
    ) val lessons: List<LessonEntity>
)

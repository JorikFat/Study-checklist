package ru.pavlig43.prototype.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CourseContentEntity(
    @Embedded val course: CourseEntity,
    @Relation(
        parentColumn = "index",
        entityColumn = "courseId"
    ) val lessons: List<LessonEntity>
)

package ru.pavlig43.prototype.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("courseId"),
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class LessonEntity(
    @PrimaryKey(autoGenerate = true) val index: Int,
    val courseId: Int,
    val name: String,
    val isChecked: Boolean = false
)

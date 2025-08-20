package ru.pavlig43.prototype.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) val index: Int,
    val name: String,
)

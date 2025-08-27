package ru.pavlig43.prototype.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
)

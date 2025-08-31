package com.example.courses.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
)

package com.example.courses.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.courses.database.dao.DAO
import com.example.courses.database.entities.CourseContentEntity
import com.example.courses.database.entities.CourseEntity
import com.example.courses.database.entities.LessonEntity

@Database(
    entities = [
        CourseEntity::class,
        LessonEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): DAO
}
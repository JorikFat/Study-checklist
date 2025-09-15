package com.example.courses.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.courses.database.dao.DAO
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
    companion object{
        fun createAppDataBase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = "app_db"
            ).build()
        }
    }
}



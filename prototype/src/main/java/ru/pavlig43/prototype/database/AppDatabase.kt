package ru.pavlig43.prototype.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.pavlig43.prototype.database.dao.DAO
import ru.pavlig43.prototype.database.entities.CourseContentEntity
import ru.pavlig43.prototype.database.entities.CourseEntity
import ru.pavlig43.prototype.database.entities.LessonEntity

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
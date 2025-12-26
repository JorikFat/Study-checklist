package com.example.courses.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.example.courses.database.entities.CourseContentEntity
import com.example.courses.database.entities.CourseEntity
import com.example.courses.database.entities.LessonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {

    @Query("SELECT * FROM CourseEntity")
    fun listen() : Flow<List<CourseContentEntity>>

    // queries
    @Transaction
    @Query("select * from CourseEntity")
    suspend fun getCourses(): List<CourseContentEntity>

    @Query("select id from CourseEntity where rowid = :rowId")
    suspend fun courseIdByRowId(rowId: Long): Int

    @Query("SELECT * FROM CourseEntity where id = :courseId")
    suspend fun getCourse(courseId :Int) :CourseContentEntity

    // courses
    @Insert
    suspend fun courseCreate(courseEntity: CourseEntity): Long

    @Delete
    suspend fun courseDelete(courseEntity: CourseEntity)

    @Update
    suspend fun courseUpdate(courseEntity: CourseEntity)

    // lessons
    @Insert
    suspend fun lessonCreate(lessonEntity: LessonEntity)

    @Delete
    suspend fun lessonDelete(lessonEntity: LessonEntity)

    @Update
    suspend fun lessonUpdate(lessonEntity: LessonEntity)

    // transactions
    @Transaction
    suspend fun courseCreate(courseContent: CourseContentEntity) {
        val courseId = courseIdByRowId(courseCreate(courseContent.course))
        courseContent.lessons.map {
            lessonCreate(
                it.copy(courseId = courseId)
            )
        }
    }

    @Transaction
    suspend fun courseUpdate(courseContent: CourseContentEntity) {
        courseUpdate(courseContent.course)
        courseContent.lessons.forEach { lessonUpdate(it) }
    }

    @Transaction
    suspend fun courseFullUpdate(
        course: CourseEntity,
        lessonsToDelete: List<LessonEntity>,
        lessonsToUpsert: List<LessonEntity>
    ) {
        courseUpdate(course)
        lessonsToDelete.forEach { lessonDelete(it) }
        lessonsToUpsert.forEach { upsertLesson(it) }
    }

    @Upsert
    suspend fun upsertLesson(lessonEntity: LessonEntity)



}
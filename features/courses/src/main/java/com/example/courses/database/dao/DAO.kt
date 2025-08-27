package com.example.courses.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.courses.database.entities.CourseContentEntity
import com.example.courses.database.entities.CourseEntity
import com.example.courses.database.entities.LessonEntity

@Dao
interface DAO {

    @Query("select * from CourseEntity")
    suspend fun getCourses(): List<CourseEntity>

    @Query("select * from CourseEntity join LessonEntity on LessonEntity.courseId = CourseEntity.id where CourseEntity.id like :courseId")
    suspend fun getCourseWithLessons(courseId: Int): CourseContentEntity


    // courses
    @Insert
    suspend fun courseCreate(courseEntity: CourseEntity)

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

}
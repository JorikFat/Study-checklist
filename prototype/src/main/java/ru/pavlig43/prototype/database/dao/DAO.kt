package ru.pavlig43.prototype.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.pavlig43.prototype.database.entities.CourseContentEntity
import ru.pavlig43.prototype.database.entities.CourseEntity
import ru.pavlig43.prototype.database.entities.LessonEntity

@Dao
interface DAO {

    @Query("select * from CourseEntity")
    suspend fun getCourses(): List<CourseEntity>

    @Query("select * from CourseEntity join LessonEntity on LessonEntity.courseId = CourseEntity.`index` where CourseEntity.`index` like :courseId")
    suspend fun getCourseWithLessons(courseId: Int): CourseContentEntity


    // courses
    @Insert
    suspend fun courseCreate(courseEntity: CourseEntity)

    @Query("delete from CourseEntity where `index` like :courseId")
    suspend fun courseDelete(courseId: Int)

    @Query("update CourseEntity set name = :newName where `index` like :courseId")
    suspend fun courseUpdateName(courseId: Int, newName: String)


    // lessons
    @Insert
    suspend fun lessonCreate(lessonEntity: LessonEntity)

    @Query("delete from LessonEntity where `index` like :lessonId")
    suspend fun lessonDelete(lessonId: Int)

    @Query("update LessonEntity set isChecked = not isChecked where `index` like :lessonId")
    suspend fun lessonSwitchChecked(lessonId: Int)

    @Query("update LessonEntity set name = :newName where `index` like :lessonId")
    suspend fun lessonUpdateName(lessonId: Int, newName: String)


}
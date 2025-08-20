package ru.pavlig43.prototype.database.repository

import ru.pavlig43.prototype.models.CourseContentViewState
import ru.pavlig43.prototype.models.CourseViewState
import ru.pavlig43.prototype.models.LessonViewState

interface CoursesRepository {

    suspend fun getCoursesOnly(): List<CourseViewState>

    suspend fun getCourseWithLessons(courseId: Int): CourseContentViewState

    suspend fun courseCreate(newCourse: CourseContentViewState)

    suspend fun courseDelete(courseId: Int)

    suspend fun courseUpdateName(courseId: Int, name: String)

    suspend fun lessonCreate(courseId: Int, newLesson: LessonViewState)

    suspend fun lessonDelete(lessonId: Int)

    suspend fun lessonUpdateName(lessonId: Int, name: String)

    suspend fun lessonCheck(lessonId: Int)




}
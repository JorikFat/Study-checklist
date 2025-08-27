package com.example.courses.database.repository

import com.example.courses.models.Course
import com.example.courses.models.Lesson

interface CoursesRepository {

    suspend fun getCoursesOnly(): List<Course>

    suspend fun getCourseWithLessons(courseId: Int): Course

    suspend fun courseCreate(newCourse: Course)

    suspend fun courseDelete(courseId: Int)

    suspend fun courseUpdateName(courseId: Int, name: String)

    suspend fun lessonCreate(courseId: Int, newLesson: Lesson)

    suspend fun lessonDelete(lessonId: Int)

    suspend fun lessonUpdateName(lessonId: Int, name: String)

    suspend fun lessonCheck(lessonId: Int)




}
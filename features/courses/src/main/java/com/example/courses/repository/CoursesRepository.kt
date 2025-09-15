package com.example.courses.repository

import com.example.courses.models.Course
import com.example.courses.models.Lesson

interface CoursesRepository {

    suspend fun getCourses(): List<Course>

    suspend fun courseCreate(course: Course)

    suspend fun courseDelete(course: Course)

    suspend fun courseUpdate(course: Course, ld: List<Lesson>)

    suspend fun lessonCreate(courseId: Int, lesson: Lesson)

    suspend fun lessonDelete(courseId: Int, lesson: Lesson)

    suspend fun lessonUpdate(courseId: Int, lesson: Lesson)

}
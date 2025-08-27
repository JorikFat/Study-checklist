package com.example.courses.database.repository

import com.example.courses.database.AppDatabase
import com.example.courses.database.entities.CourseEntity
import com.example.courses.database.entities.LessonEntity
import com.example.courses.database.mappers.toCourse
import com.example.courses.database.mappers.toEntity
import com.example.courses.models.Course
import com.example.courses.models.Lesson

class CoursesRepositoryImpl(db: AppDatabase): CoursesRepository {

    private val dao = db.getDao()

    override suspend fun getCoursesOnly(): List<Course> {
//        return dao.getCourses().map {  }
        return emptyList()
    }

    override suspend fun getCourseWithLessons(courseId: Int): Course {
        return dao.getCourseWithLessons(courseId).toCourse()
    }

    override suspend fun courseCreate(newCourse: Course) {
        with(newCourse.toEntity()) {
            dao.courseCreate(course)
            lessons.forEach { dao.lessonCreate(it) }
        }
    }

    override suspend fun courseDelete(courseId: Int) {
        dao.courseDelete(CourseEntity(courseId, ""))
    }

    override suspend fun courseUpdateName(courseId: Int, name: String) {
        dao.courseUpdate(
            CourseEntity(
                id = courseId,
                name = name
            )
        )
    }

    override suspend fun lessonCreate(courseId: Int, newLesson: Lesson) {
        dao.lessonCreate(newLesson.toEntity(courseId))
    }

    override suspend fun lessonDelete(lessonId: Int) {
        dao.lessonDelete(LessonEntity(lessonId, 0,"", false))
    }

    override suspend fun lessonUpdateName(lessonId: Int, name: String) {
        dao.lessonUpdate(
            LessonEntity(
                lessonId,
                0,
                name
            )
        )
    }

    override suspend fun lessonCheck(lessonId: Int) {
        dao.lessonUpdate(
            LessonEntity(lessonId, 0, "")
        )
    }

}
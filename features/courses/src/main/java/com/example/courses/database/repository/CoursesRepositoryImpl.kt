package com.example.courses.database.repository

import com.example.courses.database.AppDatabase
import com.example.courses.database.entities.CourseEntity
import com.example.courses.database.mappers.toCourse
import com.example.courses.database.mappers.toEntity
import com.example.courses.models.Course
import com.example.courses.models.Lesson

class CoursesRepositoryImpl(db: AppDatabase): CoursesRepository {

    private val dao = db.getDao()

    override suspend fun getCourses(): List<Course> {
       return dao.getCourses().map { it.toCourse() }
    }

    override suspend fun getCourseWithLessons(courseId: Int): Course {
        return dao.getCourseWithLessons(courseId).toCourse()
    }

    override suspend fun courseCreate(course: Course) {
        with(course.toEntity()) {
            dao.courseCreate(
                course = this.course,
                lessons = this.lessons
            )
        }
    }

    override suspend fun courseDelete(course: Course) {
        dao.courseDelete(CourseEntity(course.id, course.displayName))
    }

    override suspend fun courseUpdate(course: Course) {
        dao.courseUpdate(
            course.toEntity()
        )
    }

    override suspend fun lessonCreate(courseId: Int, lesson: Lesson) {
        dao.lessonCreate(lesson.toEntity(courseId))
    }

    override suspend fun lessonDelete(courseId: Int, lesson: Lesson) {
        dao.lessonDelete(lesson.toEntity(courseId))
    }

    override suspend fun lessonUpdate(courseId: Int, lesson: Lesson) {
        dao.lessonUpdate(lesson.toEntity(courseId))
    }

}
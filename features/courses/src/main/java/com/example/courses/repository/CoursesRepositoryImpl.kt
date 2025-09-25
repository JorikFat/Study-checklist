package com.example.courses.repository

import com.example.courses.database.AppDatabase
import com.example.courses.database.entities.CourseEntity
import com.example.courses.database.mappers.toCourse
import com.example.courses.database.mappers.toEntity
import com.example.courses.models.Course
import com.example.courses.models.Lesson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoursesRepositoryImpl(db: AppDatabase): CoursesRepository {

    private val dao = db.getDao()

    override fun listen(): Flow<List<Course>> {
        return dao.listen().map { it.map { it.toCourse() } }
    }

    override suspend fun getCourses(): List<Course> {
       return dao.getCourses().map { it.toCourse() }
    }

    override suspend fun getCourse(id: Int): Course =
        dao.getCourse(id).toCourse()

    override suspend fun courseCreate(course: Course) {
        dao.courseCreate(course.toEntity())
    }

    override suspend fun courseDelete(course: Course) {
        dao.courseDelete(CourseEntity(course.id, course.displayName))
    }

    override suspend fun courseUpdate(course: Course, deleteLessons: List<Lesson>) {
        with(course.toEntity()) {
            dao.courseFullUpdate(
                course = this.course,
                lessonsToDelete = deleteLessons.map { it.toEntity(this.course.id) },
                lessonsToUpsert = this.lessons
            )
        }
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
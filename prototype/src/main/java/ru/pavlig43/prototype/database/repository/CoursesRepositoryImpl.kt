package ru.pavlig43.prototype.database.repository

import androidx.room.util.performInTransactionSuspending
import ru.pavlig43.prototype.database.AppDatabase
import ru.pavlig43.prototype.database.entities.CourseEntity
import ru.pavlig43.prototype.database.entities.LessonEntity
import ru.pavlig43.prototype.database.mappers.toEntity
import ru.pavlig43.prototype.database.mappers.toViewState
import ru.pavlig43.prototype.models.CourseContentViewState
import ru.pavlig43.prototype.models.CourseViewState
import ru.pavlig43.prototype.models.LessonViewState

class CoursesRepositoryImpl(db: AppDatabase): CoursesRepository {

    private val dao = db.getDao()

    override suspend fun getCoursesOnly(): List<CourseViewState> {
        return dao.getCourses().map { it.toViewState() }
    }

    override suspend fun getCourseWithLessons(courseId: Int): CourseContentViewState {
        return dao.getCourseWithLessons(courseId).toViewState()
    }

    override suspend fun courseCreate(newCourse: CourseContentViewState) {
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

    override suspend fun lessonCreate(courseId: Int, newLesson: LessonViewState) {
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
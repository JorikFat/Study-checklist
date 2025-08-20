package ru.pavlig43.prototype.database.repository

import ru.pavlig43.prototype.database.AppDatabase
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
        dao.courseDelete(courseId)
    }

    override suspend fun courseUpdateName(courseId: Int, name: String) {
        dao.courseUpdateName(
            courseId = courseId,
            newName = name
        )
    }

    override suspend fun lessonCreate(courseId: Int, newLesson: LessonViewState) {
        dao.lessonCreate(newLesson.toEntity(courseId))
    }

    override suspend fun lessonDelete(lessonId: Int) {
        dao.lessonDelete(lessonId)
    }

    override suspend fun lessonUpdateName(lessonId: Int, name: String) {
        dao.lessonUpdateName(
            lessonId = lessonId,
            newName = name
        )
    }

    override suspend fun lessonCheck(lessonId: Int) {
        dao.lessonSwitchChecked(lessonId)
    }

}
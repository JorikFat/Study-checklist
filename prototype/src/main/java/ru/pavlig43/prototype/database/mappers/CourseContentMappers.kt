package ru.pavlig43.prototype.database.mappers

import ru.pavlig43.prototype.database.entities.CourseContentEntity
import ru.pavlig43.prototype.models.CourseContentViewState

fun CourseContentEntity.toViewState(): CourseContentViewState {
    return CourseContentViewState(
        course = course.toViewState(),
        lessons = lessons.map { it.toViewState() }
    )
}

fun CourseContentViewState.toEntity(): CourseContentEntity {
    return CourseContentEntity(
        course = course.toEntity(),
        lessons = lessons.map { it.toEntity(course.index) }
    )
}
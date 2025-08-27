package ru.pavlig43.prototype.database.mappers

import ru.pavlig43.prototype.database.entities.CourseEntity
import ru.pavlig43.prototype.models.CourseViewState

fun CourseEntity.toViewState(): CourseViewState {
    return CourseViewState(
        id,
        name
    )
}

fun CourseViewState.toEntity(): CourseEntity {
    return CourseEntity(
        index,
        name
    )
}
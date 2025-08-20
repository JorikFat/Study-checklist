package ru.pavlig43.prototype.database.mappers

import ru.pavlig43.prototype.database.entities.LessonEntity
import ru.pavlig43.prototype.models.LessonViewState

fun LessonEntity.toViewState(): LessonViewState {
    return LessonViewState(
        index = index,
        name = name,
        isChecked = isChecked
    )
}

fun LessonViewState.toEntity(courseId: Int): LessonEntity {
    return LessonEntity(
        index = index,
        courseId = courseId,
        name = name,
        isChecked = isChecked
    )
}
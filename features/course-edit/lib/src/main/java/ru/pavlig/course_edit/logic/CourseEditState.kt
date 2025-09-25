package ru.pavlig.course_edit.logic

import ru.pavlig.course_edit.logic.models.CourseDraft

sealed interface CourseEditState {
    data object Loading :CourseEditState
    data class Data(val draft :CourseDraft) :CourseEditState
}
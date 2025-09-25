package com.example.courses.edit

import com.example.courses.edit.models.CourseDraft
import com.example.courses.edit.models.LessonDraft
import com.example.courses.models.Course
import com.example.courses.models.Lesson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CourseDraftEditor(course: Course?) {

    private val state = MutableStateFlow(course?.let(::CourseDraft) ?: CourseDraft())
    val flow = state.asStateFlow()
    val course: Course get() = state.value.course

    fun changeCourseName(name: String) =
        state.update { it.copy(name = name) }

    fun changeLessonName(index: Int, name: String) =
        state.update { course ->
            course.copy(
                lessons = course.lessons.toMutableList()
                    .also { it[index] = it[index].copy(name = name) }
            )
        }

    fun addLesson() =
        state.update { course ->
            course.copy(
                lessons = course.lessons.plus(LessonDraft())
            )
        }

    fun deleteLesson(index: Int) =
        state.update { course ->
            course.copy(
                lessons = course.lessons.minus(
                    course.lessons[index]
                )
            )
        }

    private val CourseDraft.course: Course
        get() = Course(
            id = id,
            displayName = name,
            lessons = lessons.map { it.lesson }
        )

    private val LessonDraft.lesson: Lesson
        get() = Lesson(
            id = id,
            name = name,
            isChecked = isChecked
        )
}
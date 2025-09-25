package ru.pavlig.course_edit.logic

import ru.pavlig.course_edit.logic.models.CourseDraft
import ru.pavlig.course_edit.logic.models.LessonDraft
import com.example.courses.models.Course
import com.example.courses.models.Lesson

class CourseDraftEditor(course: Course?) {
    var draft: CourseDraft = course?.let(::CourseDraft) ?: CourseDraft()
        private set
    val course: Course get() = draft.course

    fun changeCourseName(name: String) {
        draft = draft.copy(name = name)
    }

    fun changeLessonName(index: Int, name: String) {
        draft = draft.copy(
            lessons = draft.lessons.toMutableList()
                .also { it[index] = it[index].copy(name = name) }
        )
    }

    fun addLesson() {
        draft = draft.copy(
            lessons = draft.lessons.plus(LessonDraft())
        )
    }

    fun deleteLesson(index: Int) {
        draft = draft.copy(
            lessons = draft.lessons.minus(
                draft.lessons[index]
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
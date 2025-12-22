package ru.pavlig.course_edit.logic

import ru.pavlig.course_edit.logic.models.CourseDraft
import ru.pavlig.course_edit.logic.models.LessonDraft
import com.example.courses.models.Course
import com.example.courses.models.Lesson

class CourseDraftEditor(
    val srcCourse: Course?
) {
    private var name: String = srcCourse?.displayName ?: ""
    private val lessons: MutableList<LessonDraft> = srcCourse
        ?.lessons
        ?.map(::LessonDraft)
        ?.toMutableList()
        ?: mutableListOf(LessonDraft())

    val state: CourseDraft
        get() = CourseDraft(
            id = srcCourse?.id ?: 0,
            name = name,
            lessons = lessons
        )
    val course: Course get() = state.course

    fun changeCourseName(name: String) {
        this.name = name
    }

    fun changeLessonName(index: Int, name: String) {
        lessons[index] = lessons[index].copy(name = name)
    }

    fun addLesson() =
        lessons.add(LessonDraft())

    fun deleteLesson(index: Int) =
        lessons.removeAt(index)

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
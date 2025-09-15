package com.example.courses.repository

import com.example.courses.models.Course
import com.example.courses.models.Lesson

class FakeCoursesRepository : CoursesRepository {

    private val stubCourses :MutableList<Course> = Course.Stub.courses.toMutableList()

    override suspend fun getCourses(): List<Course> =
        stubCourses.toList()

    override suspend fun courseCreate(course: Course) {
        val lastId = stubCourses.lastOrNull()?.id ?: 0
        stubCourses.add(course.copy(id = lastId + 1))
    }

    override suspend fun courseDelete(course: Course) {
        stubCourses.removeIf { it.id == course.id }
    }

    override suspend fun courseUpdate(course: Course, ld: List<Lesson>) {
        val courseIndex = courseIndexById(course.id)
        stubCourses[courseIndex] = course
    }

    override suspend fun lessonCreate(courseId: Int, lesson: Lesson) {
        val courseIndex = courseIndexById(courseId)
        val course = stubCourses[courseIndex]
        val lessons = course.lessons.toMutableList()
        val lastId = lessons.lastOrNull()?.id ?: 0
        lessons.add(lesson.copy(id = lastId + 1))
        stubCourses[courseIndex] = course.copy(lessons = lessons)
    }

    override suspend fun lessonDelete(courseId: Int, lesson: Lesson) {
        val courseIndex = courseIndexById(courseId)
        val course = stubCourses[courseIndex]
        val lessons = course.lessons.toMutableList()
        val lessonIndex = lessons.indexOfFirst { it.id == lesson.id }
        lessons.removeAt(lessonIndex)
        stubCourses[courseIndex] = course.copy(lessons = lessons)
    }

    override suspend fun lessonUpdate(courseId: Int, lesson: Lesson) {
        val courseIndex = courseIndexById(courseId)
        val course = stubCourses[courseIndex]
        val lessons = course.lessons.toMutableList()
        val lessonIndex = lessons.indexOfFirst { it.id == lesson.id }
        lessons[lessonIndex] = lesson
        stubCourses[courseIndex] = course.copy(lessons = lessons)
    }

    private fun courseIndexById(id :Int) :Int = stubCourses.indexOfFirst { it.id == id }
}

private fun <T, R> List<T>.replaceFirstBy(item :T, selector: (T)->R) : List<T> {
    val mutable = toMutableList()
    val index = indexOfFirst { selector(item) == selector(it) }
    mutable[index] = item
    return mutable.toList()
}
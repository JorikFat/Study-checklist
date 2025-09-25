package dev.jorikfat.studyChecklist.features.courses

import com.example.courses.models.Course
import com.example.courses.models.Lesson
import com.example.courses.repository.FakeCoursesRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeRepositoryUnitTest {

    private lateinit var repository :FakeCoursesRepository

    @Before
    fun setup(){
        repository = FakeCoursesRepository()
    }

    @Test
    fun initialState() = runTest {
        assertEquals(Course.Stub.courses, repository.getCourses())
    }

    @Test
    fun createCourse() = runTest {
        val newLesson = Lesson(10, "new lesson", false)
        val newCourse = Course(4, "new course", listOf(newLesson))
        repository.courseCreate(newCourse)
        assertEquals(Course.Stub.courses + newCourse, repository.getCourses())
    }

    @Test
    fun deleteCourse() = runTest {
        val removed = Course.Stub.courses.first()
        repository.courseDelete(removed)
        assertEquals(Course.Stub.courses - removed, repository.getCourses())
    }

    @Test
    fun updateCourse() = runTest {
        val updated = Course.Stub.courses.first().copy(displayName = "update name")
//        repository.courseUpdate(updated, 1)//FIXME
        val expected = Course.Stub.courses.toMutableList().also { it[0] = updated }
        assertEquals(expected, repository.getCourses())
    }

    @Test
    fun createLesson() = runTest {
        val newLesson = Lesson(8, "new lesson", false)
        repository.lessonCreate(3, newLesson)
        val expected = Course.Stub.courses[2].let { it.copy(lessons = it.lessons + newLesson) }
        assertEquals(expected, repository.getCourses()[2])
    }

    @Test
    fun deleteLesson() = runTest {
        val editedCourse = Course.Stub.courses[0].copy()
        val removedLesson = editedCourse.lessons.first()
        repository.lessonDelete(editedCourse.id, removedLesson)
        val expected = editedCourse.let { it.copy(lessons = it.lessons - removedLesson) }
        assertEquals(expected, repository.getCourses()[0])
    }

    @Test
    fun updateLesson() = runTest {
        val editedCourse = Course.Stub.courses[0].copy()
        val editedLesson = editedCourse.lessons.first().copy(name = "new name")
        repository.lessonUpdate(editedCourse.id, editedLesson)
        val expected = editedCourse.let { it.copy(lessons = it.lessons.toMutableList().apply { this[0] = editedLesson }) }
        assertEquals(expected, repository.getCourses()[0])
    }
}
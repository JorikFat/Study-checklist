package com.example.courses

import com.example.courses.data.CourseMenuItemData
import com.example.courses.data.courseList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

object CourseInteractor {
    private val _courseMenuList = MutableStateFlow<List<CourseMenuItemData>>(courseList)
    val courseMenuList = _courseMenuList.asStateFlow()

    fun courseById(id: Int): Flow<CourseMenuItemData> {
        return _courseMenuList.map { lst -> lst.find { it.id == id }?:lst.first{it.id == 1} }
    }

    fun getCourseById(id: Int): CourseMenuItemData {
        return _courseMenuList.value.find { it.id == id } ?: CourseMenuItemData()
    }


    fun createCourse(course: CourseMenuItemData) {
        val lastIndex = _courseMenuList.value.maxOfOrNull { it.id }
        updateCourseList { it.add(course.copy(id = lastIndex?.plus(1) ?: 1)) }
    }

    fun updateCourse(course: CourseMenuItemData) {
        updateCourseList { lst ->
            lst.removeIf { course.id == it.id }
            lst.add(course)
        }
    }


    private fun updateCourseList(action: (MutableList<CourseMenuItemData>) -> Unit) {
        val updatedList = _courseMenuList.value.toMutableList()
        action(updatedList)
        _courseMenuList.update { updatedList }
    }
}
package com.example.courses.database.mappers

import com.example.courses.database.entities.CourseContentEntity
import com.example.courses.database.entities.CourseEntity
import com.example.courses.models.Course

fun CourseContentEntity.toCourse(): Course {
    return Course(
        id = this.course.id,
        displayName = this.course.name,
        lessons = this.lessons.map { it.toLesson() }
    )
}

fun Course.toEntity(): CourseContentEntity {
    return CourseContentEntity(
        course = CourseEntity(
            id = this.id,
            name = this.displayName
        ),
        lessons = this.lessons.map { it.toEntity(this.id) }
    )
}
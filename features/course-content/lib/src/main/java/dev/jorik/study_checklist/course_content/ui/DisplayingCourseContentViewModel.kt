package dev.jorik.study_checklist.course_content.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.CourseInteractor
import com.example.courses.data.CourseMenuItemData
import com.example.courses.data.LessonData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DisplayingCourseContentViewModel(
    id: Int
) : ViewModel() {
    val courseContent = CourseInteractor.courseById(id).map { it.mapToCourseContent() }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        CourseContent()
    )



    fun onCheckedChange(index: Int, isChecked: Boolean) {
        val updatedLessons = courseContent.value.lessons.map { lesson ->
            if (lesson.index == index) {
                lesson.copy(isChecked = isChecked)
            } else {
                lesson
            }
        }
        val updatedCourse = courseContent.value.copy(
            lessons = updatedLessons
        )
        CourseInteractor.updateCourse(updatedCourse.mapToCourseMenuItemData())

    }

}

private fun CourseMenuItemData.mapToCourseContent(): CourseContent {
    return CourseContent(
        id = id,
        name = displayName,
        lessons = lessons.map { it.mapToLesson() }
    )
}

private fun LessonData.mapToLesson(): Lesson {
    return Lesson(
        index = id,
        name = name,
        isChecked = isChecked
    )
}
private fun CourseContent.mapToCourseMenuItemData(): CourseMenuItemData {
    return CourseMenuItemData(
        id = id,
        displayName = name,
        lessons = lessons.map { it.toLessonData() }
    )
}
private fun Lesson.toLessonData(): LessonData {
    return LessonData(
        id = index,
        name = name,
        isChecked = isChecked
    )
}

data class CourseContent(
    val id: Int = 0,
    val name: String = "SOLID",
    val lessons: List<Lesson> = listOf(
        "SRP",
        "OCP",
        "LSP",
        "ISP",
        "DIP",
    ).mapIndexed { index, lesson -> Lesson(index, lesson) }
)

data class Lesson(
    val index: Int,
    val name: String,
    val isChecked: Boolean = false
)
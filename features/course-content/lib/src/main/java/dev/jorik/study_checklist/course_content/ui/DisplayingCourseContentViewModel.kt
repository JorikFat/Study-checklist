package dev.jorik.study_checklist.course_content.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import com.example.courses.models.Lesson
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DisplayingCourseContentViewModel(
   private val id: Int
) : ViewModel() {
    val courseState = CourseInteractor.courseMenuList
        .map {lst-> (lst.find { it.id == id }?:Course()).toViewState() }
            .stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        CourseViewState()
    )


    fun toggleLesson(lessonId: Int){
        CourseInteractor.toggleLesson(
            courseId = id,
            lessonId = lessonId
        )
    }

}

private fun Course.toViewState(): CourseViewState {
    return CourseViewState(
        id = id,
        name = displayName,
        lessons = lessons.map { it.mapToLesson() }
    )
}

private fun Lesson.mapToLesson(): LessonViewState {
    return LessonViewState(
        index = id,
        name = name,
        isChecked = isChecked
    )
}

data class CourseViewState(
    val id: Int = 0,
    val name: String = "SOLID",
    val lessons: List<LessonViewState> = listOf(
        "SRP",
        "OCP",
        "LSP",
        "ISP",
        "DIP",
    ).mapIndexed { index, lesson -> LessonViewState(index, lesson) }
)

data class LessonViewState(
    val index: Int,
    val name: String,
    val isChecked: Boolean = false
)
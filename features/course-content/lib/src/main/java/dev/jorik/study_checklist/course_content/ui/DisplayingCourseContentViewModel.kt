package dev.jorik.study_checklist.course_content.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.CourseInteractor
import com.example.courses.models.Course
import com.example.courses.models.Lesson
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//TODO: rename to ContentViewModel
class DisplayingCourseContentViewModel(
    private val id: Int,
    private val courseInteractor: CourseInteractor,
) : ViewModel() {
    private val courseStateList = courseInteractor.courseMenuList

    private val _courseState = MutableStateFlow(CourseViewState())
    val courseState = _courseState.asStateFlow()

    private var job: Job? = null

    fun startJob() {
        job?.cancel()
        job = viewModelScope.launch {
            courseStateList.collect { lst ->
                _courseState.update { lst.first { it.id == id }.toViewState() }
            }
        }
    }
    fun stopJob() {
        job?.cancel()
        job = null
    }


    fun toggleLesson(lessonId: Int) {
        viewModelScope.launch {
            courseInteractor.toggleLesson(
                courseId = id,
                lessonId = lessonId
            )
        }
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
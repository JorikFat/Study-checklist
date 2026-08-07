package dev.jorik.study_checklist.course_content

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.isOff
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.runBlocking
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.room.Room
import com.example.courses.CourseInteractor
import com.example.courses.database.AppDatabase
import com.example.courses.database.entities.CourseEntity
import com.example.courses.database.entities.LessonEntity
import com.example.courses.repository.CoursesRepositoryImpl
import dev.jorik.study_checklist.course_content.ui.DisplayCourseContentLayout
import dev.jorik.study_checklist.course_content.ui.DisplayingCourseContentViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CourseCheckboxPersistenceTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val currentViewModel = mutableStateOf<DisplayingCourseContentViewModel?>(null)
    private lateinit var courseInteractor: CourseInteractor

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.deleteDatabase("app_db")
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
        val dao = db.getDao()
        val courseRowId = runBlocking { dao.courseCreate(CourseEntity(name = "SOLID")) }
        val courseId = runBlocking { dao.courseIdByRowId(courseRowId) }
        listOf("SRP", "OCP", "LSP", "ISP", "DIP").forEach { lessonName ->
            runBlocking {
                dao.lessonCreate(
                    LessonEntity(
                        courseId = courseId,
                        name = lessonName,
                        isChecked = false
                    )
                )
            }
        }
        courseInteractor = CourseInteractor(CoursesRepositoryImpl(db))

        composeTestRule.setContent {
            val viewModel = currentViewModel.value
            if (viewModel != null) {
                val state by viewModel.courseState.collectAsState()
                DisplayCourseContentLayout(
                    course = state,
                    onEditButtonClick = {},
                    onBackButtonClick = {},
                    toggleLesson = viewModel::toggleLesson,
                )
            }
        }
    }

    @Test
    fun checkboxStatePersistsAcrossCourseReopen() {
        val firstOpen = openCourse()
        composeTestRule.onAllNodes(isToggleable()).assertAll(isOff())

        toggle(0)
        assertChecked(0, true)
        closeCourse(firstOpen)

        val secondOpen = openCourse()
        assertChecked(0, true)

        toggle(1)
        assertChecked(1, true)
        toggle(2)
        assertChecked(2, true)
        toggle(0)
        assertChecked(0, false)
        closeCourse(secondOpen)

        val thirdOpen = openCourse()
        assertChecked(0, false)
        assertChecked(1, true)
        assertChecked(2, true)
        closeCourse(thirdOpen)
    }

    private fun openCourse(): DisplayingCourseContentViewModel {
        val viewModel = DisplayingCourseContentViewModel(id = 1, courseInteractor = courseInteractor)
        viewModel.startJob()
        currentViewModel.value = viewModel
        waitForToggleables()
        return viewModel
    }

    private fun closeCourse(viewModel: DisplayingCourseContentViewModel) {
        viewModel.stopJob()
        currentViewModel.value = null
    }

    private fun toggle(index: Int) {
        composeTestRule.onAllNodes(isToggleable())[index].performClick()
    }

    private fun assertChecked(index: Int, expected: Boolean) {
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            runCatching {
                val state = composeTestRule.onAllNodes(isToggleable())[index]
                    .fetchSemanticsNode()
                    .config[SemanticsProperties.ToggleableState]
                state == if (expected) ToggleableState.On else ToggleableState.Off
            }.getOrDefault(false)
        }
    }

    private fun waitForToggleables() {
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            runCatching {
                composeTestRule.onAllNodes(isToggleable()).fetchSemanticsNodes().size >= 3
            }.getOrDefault(false)
        }
    }
}

package dev.jorik.study_checklist.course_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import ru.pavlig43.courses_list_impl.data.CourseItemViewState
import ru.pavlig43.courses_list_impl.ui.CoursesLayout

class UITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showStubCourses() {
        val stubCourses = listOf(
            CourseItemViewState(0, "SOLID"),
            CourseItemViewState(1, "Clean Architecture"),
            CourseItemViewState(2, "Design Patterns")
        )
        composeTestRule.setContent {
            CoursesLayout(
                courses = stubCourses,
                onContentScreen = {},
                onEditScreen = {}
            )
        }

        composeTestRule.onNodeWithText("SOLID").assertIsDisplayed()
        composeTestRule.onNodeWithText("Clean Architecture").assertIsDisplayed()
        composeTestRule.onNodeWithText("Design Patterns").assertIsDisplayed()
    }

    @Test
    fun showStubCourses2() {
        composeTestRule.setContent {
            CoursesLayout(
                courses = listOf(
                    CourseItemViewState(0, "Dagger2"),
                    CourseItemViewState(1, "Kotlin Coroutines"),
                    CourseItemViewState(2, "Custom View"),
                ),
                onEditScreen = {},
                onContentScreen = {},
            )
        }

        composeTestRule.onNodeWithText("Dagger2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Kotlin Coroutines").assertIsDisplayed()
        composeTestRule.onNodeWithText("Custom View").assertIsDisplayed()
    }
}
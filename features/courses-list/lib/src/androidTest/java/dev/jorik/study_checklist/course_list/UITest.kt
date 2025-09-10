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
        composeTestRule.setContent {
            CoursesLayout(
                courses = listOf(
                    CourseItemViewState(1, "SOLID"),
                    CourseItemViewState(2, "Clean Architecture"),
                    CourseItemViewState(3, "Design Patterns"),
                ),
                onContentScreen = {},
                onAddButtonClick = {}
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
                    CourseItemViewState(1, "Dagger2"),
                    CourseItemViewState(2, "Kotlin Coroutines"),
                    CourseItemViewState(3, "Custom View"),
                ),
                onContentScreen = {},
                onAddButtonClick = {}
            )
        }

        composeTestRule.onNodeWithText("Dagger2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Kotlin Coroutines").assertIsDisplayed()
        composeTestRule.onNodeWithText("Custom View").assertIsDisplayed()
    }
}
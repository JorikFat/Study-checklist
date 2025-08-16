package dev.jorik.study_checklist.course_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import ru.pavlig43.courses_list_impl.data.Course
import ru.pavlig43.courses_list_impl.ui.CoursesLayout

class UITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showStubCourses() {
        composeTestRule.setContent {
            CoursesLayout(
                courses = listOf(
                    Course("SOLID"),
                    Course("Clean Architecture"),
                    Course("Design Patterns"),
                ),
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
                    Course("Dagger2"),
                    Course("Kotlin Coroutines"),
                    Course("Custom View"),
                ),
            )
        }

        composeTestRule.onNodeWithText("Dagger2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Kotlin Coroutines").assertIsDisplayed()
        composeTestRule.onNodeWithText("Custom View").assertIsDisplayed()
    }
}
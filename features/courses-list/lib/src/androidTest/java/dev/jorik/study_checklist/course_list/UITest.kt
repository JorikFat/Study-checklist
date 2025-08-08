package dev.jorik.study_checklist.course_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import ru.pavlig43.courses_list_impl.ui.CoursesScreen

class UITest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun showStubCourses() {
        composeTestRule.setContent {
            CoursesScreen({},{})
        }

        composeTestRule.onNodeWithText("SOLID").assertIsDisplayed()
        composeTestRule.onNodeWithText("Clean Architecture").assertIsDisplayed()
        composeTestRule.onNodeWithText("Design Patterns").assertIsDisplayed()
    }
}
package dev.jorik.study_checklist.course_content

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.jorik.study_checklist.course_content.ui.CourseViewState
import dev.jorik.study_checklist.course_content.ui.DisplayCourseContentLayout
import dev.jorik.study_checklist.course_content.ui.LessonViewState
import org.junit.Rule
import org.junit.Test

class UITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showStubCourse() {
        composeTestRule.setContent {
            val stubCourse = CourseViewState(
                name = "SOLID",
                lessons = listOf(
                    "SRP",
                    "OCP",
                    "LSP",
                    "ISP",
                    "DIP",
                ).mapIndexed { index, s -> LessonViewState(index,s) }
            )


            DisplayCourseContentLayout(
                course = stubCourse,
                onEditButtonClick = {},
                onBackButtonClick = {},
                toggleLesson = {},
            )
        }

        composeTestRule.onNodeWithText("SOLID").assertIsDisplayed()
        composeTestRule.onNodeWithText("SRP").assertIsDisplayed()
        composeTestRule.onNodeWithText("OCP").assertIsDisplayed()
        composeTestRule.onNodeWithText("LSP").assertIsDisplayed()
        composeTestRule.onNodeWithText("ISP").assertIsDisplayed()
        composeTestRule.onNodeWithText("DIP").assertIsDisplayed()
    }
}
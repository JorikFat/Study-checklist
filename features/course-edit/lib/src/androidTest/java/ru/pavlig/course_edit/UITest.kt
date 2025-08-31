package ru.pavlig.course_edit

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import ru.pavlig.course_edit.ui.CourseDraftViewState
import ru.pavlig.course_edit.ui.CourseEditingLayout
import ru.pavlig.course_edit.ui.LessonDraftViewState

class UITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showStubCourse() {
        composeTestRule.setContent {
            val stubCourse = CourseDraftViewState(
                name = "SOLID",
                lessons = listOf(
                    "SRP",
                    "OCP",
                    "LSP",
                    "ISP",
                    "DIP",
                ).mapIndexed { index, s -> LessonDraftViewState(index,s) }
            )


            CourseEditingLayout(
                course = stubCourse,
                onChangeCourseName = {},
                onChangeLessonName = {_, _ -> },
                onAddLesson = {},
                onDeleteLesson = {},
                onSave = {},
                onDeleteCourse = {},
                onNavigateBack = {},
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
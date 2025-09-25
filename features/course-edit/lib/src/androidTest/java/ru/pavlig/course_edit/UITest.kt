package ru.pavlig.course_edit

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import ru.pavlig.course_edit.logic.models.CourseDraft
import ru.pavlig.course_edit.logic.models.LessonDraft
import org.junit.Rule
import org.junit.Test

class UITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showStubCourse() {
        composeTestRule.setContent {
            val stubCourse = CourseDraft(
                name = "SOLID",
                lessons = listOf(
                    LessonDraft(1, "SRP", false),
                    LessonDraft(2, "OCP", false),
                    LessonDraft(3, "LSP", false),
                    LessonDraft(4, "ISP", false),
                    LessonDraft(5, "DIP", false),
                )
            )


            CourseEditingLayout(
                draft = stubCourse,
                onChangeCourseName = {},
                onChangeLessonName = { _, _ -> },
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
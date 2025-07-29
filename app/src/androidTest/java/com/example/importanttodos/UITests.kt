package com.example.importanttodos

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val originalTitle = "Buy chocolate chip cookies"
    private val updatedTitle = "Buy ice cream"

    @Test
    fun addTodo_shouldDisplayItInTheList() {
        onView(withId(R.id.todo_title)).perform(typeText(originalTitle), closeSoftKeyboard())
        onView(withId(R.id.todo_save_button)).perform(click())

        // Check if the newly added item is displayed
        onView(allOf(withId(R.id.todo_name), withText(originalTitle)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun updateTodo_shouldChangeTheDisplayedTitle() {
        // Add an item
        onView(withId(R.id.todo_title)).perform(typeText(originalTitle), closeSoftKeyboard())
        onView(withId(R.id.todo_save_button)).perform(click())

        // Click the first matching item with the original text
        onView(first(allOf(withId(R.id.todo_name), withText(originalTitle)))).perform(click())

        // Replace the text and update
        onView(withId(R.id.todo_name)).perform(replaceText(updatedTitle), closeSoftKeyboard())
        onView(withId(R.id.update_button)).perform(click())

        // Verify that the updated text is displayed
        onView(allOf(withId(R.id.todo_name), withText(updatedTitle)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun deleteTodo_shouldRemoveItFromTheList() {
        // Add an item
        onView(withId(R.id.todo_title)).perform(typeText(originalTitle), closeSoftKeyboard())
        onView(withId(R.id.todo_save_button)).perform(click())

        // Update the item
        onView(first(allOf(withId(R.id.todo_name), withText(originalTitle)))).perform(click())
        onView(withId(R.id.todo_name)).perform(replaceText(updatedTitle), closeSoftKeyboard())
        onView(withId(R.id.update_button)).perform(click())

        // Delete the item
        onView(first(allOf(withId(R.id.todo_name), withText(updatedTitle)))).perform(click())
        onView(withId(R.id.delete_button)).perform(click())

        // Verify that the item is no longer in the list
        onView(withId(R.id.todos_list))
            .check(matches(not(hasDescendant(withText(updatedTitle)))))
    }

    // Custom matcher to return the first view that matches the given condition
    private fun first(matcher: Matcher<View>): Matcher<View> {
        var isFirst = true
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Return the first matching view.")
            }

            override fun matchesSafely(view: View): Boolean {
                if (matcher.matches(view) && isFirst) {
                    isFirst = false
                    return true
                }
                return false
            }
        }
    }
}
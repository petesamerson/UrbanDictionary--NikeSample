package com.example.urban

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.urban.CustomAssertions.Companion.hasItemCount
import com.example.urban.resource.EspressoIdlingResource
import com.example.urban.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @Test
    fun onLaunchCheckAmountInputIsDisplayed(){
        ActivityScenario.launch(MainActivity::class.java)


        onView(withId(R.id.et_search_bar)).perform(typeText("words"))
        onView(withId(R.id.iv_search_icon)).perform(click())

        onView(withId(R.id.rcycl_terms))
            .check(hasItemCount(10))

    }
}
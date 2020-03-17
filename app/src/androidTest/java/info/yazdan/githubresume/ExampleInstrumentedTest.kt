package info.yazdan.githubresume

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import info.yazdan.githubresume.presentation.search.SearchActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun search_username_test() {
        val id = "fabpot"
        Espresso.onView(withId(R.id.username_input)).perform(ViewActions.typeText(id))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.generate_button)).perform(ViewActions.click())
        Thread.sleep(3000)
        Espresso.onView(withId(R.id.toolbar)).check(matches(hasDescendant(withText(id))))
        Espresso.onView(withId(R.id.link_button)).perform(ViewActions.click())
        Thread.sleep(4000)
    }

}

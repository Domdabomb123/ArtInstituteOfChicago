package dominicschumerth.artinstituteofchicago.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dominicschumerth.artinstituteofchicago.ui.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtListComposeTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltTestRule.inject()
    }

    @Test
    fun artListTest() {
        composeTestRule.onNodeWithText("My Collection").assertIsDisplayed()
        composeTestRule.onNodeWithText("Next").assertIsDisplayed()
    }

    @Test
    fun artListPagingTest() {
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Previous").assertIsDisplayed()
    }

}
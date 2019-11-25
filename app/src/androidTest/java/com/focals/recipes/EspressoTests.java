package com.focals.recipes;


import com.focals.recipes.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoTests {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickRecipe_OpensDetailActivity() {

        // Click Recipe
        onView(withText("Nutella Pie")).perform(click());

        // Verify Detail Activity is open
        onView(withText("Ingredients")).check(matches(isDisplayed()));
        onView(withId(R.id.rv_steps)).check(matches(isDisplayed()));
    }

    @Test
    public void clickIngredients_DisplaysIngredientsList() {

        // Click Recipe
        onView(withText("Nutella Pie")).perform(click());

        // Click Ingredients Button
        onView(withText("Ingredients")).perform(click());

        // Verify Ingredients List is displayed
        onView(withId(R.id.lv_ingredients)).check(matches(isDisplayed()));
    }

    @Test
    public void clickStep_DisplaysStep() {

        // Click Recipe
        onView(withText("Nutella Pie")).perform(click());

        // Click a Step
        onView(withId(R.id.rv_steps)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Verify Step Details
        onView(withId(R.id.playerView)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_stepDesc)).check(matches(isDisplayed()));
        onView(withId(R.id.button_previousStep)).check(matches(isDisplayed()));
        onView(withId(R.id.button_nextStep)).check(matches(isDisplayed()));
    }
}
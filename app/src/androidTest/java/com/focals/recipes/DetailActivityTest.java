package com.focals.recipes;

import android.content.Context;

import com.focals.recipes.activity.RecipeDetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<RecipeDetailActivity> detailActivityActivityTestRule = new ActivityTestRule<>(RecipeDetailActivity.class);

    private Context context;

    @Before
    public void init() {
        context = detailActivityActivityTestRule.getActivity().getBaseContext();
    }

    @Test
    public void clickIngredients_DisplaysIngredientsList() {

        // Click Ingredients Button
        onView(withText(context.getString(R.string.ingredientsLabel))).perform(click());

        // Verify Ingredients List is displayed
        onView(withId(R.id.lv_ingredients)).check(matches(isDisplayed()));

        System.out.println();


    }

    @Test
    public void clickStep_DisplaysStep() {

        // Click Ingredients Button
        onView(withText(context.getString(R.string.ingredientsLabel))).perform(click());

    }


}

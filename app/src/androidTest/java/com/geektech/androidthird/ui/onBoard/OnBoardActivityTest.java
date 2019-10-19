package com.geektech.androidthird.ui.onBoard;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.geektech.androidthird.R;
import com.geektech.androidthird.ui.main.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
@LargeTest
public class OnBoardActivityTest {
    @Rule
    public ActivityTestRule<OnBoardActivity> mActivityTestRule =
            new ActivityTestRule<>(OnBoardActivity.class, true, false);

    @Before
    public void setUp(){
        Intent intent = new Intent();
        mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void testNextButtonClick(){
        onView(withText("В данном приложении можете учиться))")).check(matches(isDisplayed()));
    }

    @Test
    public void testOnBoardActivityFinishIntro() throws InterruptedException{
        onView(withId(R.id.it_button)).perform(click());
        Thread.sleep(200L);

        onView(withId(R.id.it_button)).perform(click());
        Thread.sleep(200L);

        onView(withId(R.id.it_button)).perform(click());
        Thread.sleep(200L);

        Intents.init();

        onView(withId(R.id.it_button)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));

        Intents.release();
        Thread.sleep(200L);
    }
    @After
    public void finishActivity(){mActivityTestRule.finishActivity();}


}


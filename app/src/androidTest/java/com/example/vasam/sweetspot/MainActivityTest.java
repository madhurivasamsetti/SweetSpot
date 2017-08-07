package com.example.vasam.sweetspot;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;

/**
 * Created by vasam on 8/7/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    public final String CARD_TITLE = "Yellow Cake";
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onLoaded_checkTitleOnCard() {
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.
                scrollToHolder(withHolderImageTitle(CARD_TITLE)));
    }

    public static Matcher<RecyclerView.ViewHolder> withHolderImageTitle(final String text) {
        return new BoundedMatcher<RecyclerView.ViewHolder, RecipeCardsAdapter.RecipeCardHolder>
                (RecipeCardsAdapter.RecipeCardHolder.class) {
            @Override
            protected boolean matchesSafely(RecipeCardsAdapter.RecipeCardHolder item) {
                TextView titleTextView = (TextView) item.itemView.findViewById(R.id.title_text_view);
                if(titleTextView == null)
                {
                    return false;
                }
                return titleTextView.getText().toString().contains(text);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("No ViewHolder found with title: " + text);
            }
        };
    }
}

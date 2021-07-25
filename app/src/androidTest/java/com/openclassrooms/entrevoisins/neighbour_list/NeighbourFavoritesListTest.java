package com.openclassrooms.entrevoisins.neighbour_list;


import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)

public class NeighbourFavoritesListTest {

    private ListNeighbourActivity mActivity;
    private NeighbourApiService service;


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        service = DI.getNewInstanceApiService();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**we ensure that our favorite list is empty when app is launched
     * opening on the mainActivity who load and shows 'My Neighbours' List,
     * with no neighbour in the 'favorites' tab list.*/
    @Test
    public void favorite_list_starts_empty() {

        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        onView(withId(R.id.list_neighbours)).check(matches(hasMinimumChildCount(1)));
        onView(withId(R.id.main_content)).perform(swipeLeft());
        onView(withId(R.id.list_favorites)).check(matches(isDisplayed()));
        onView(withId(R.id.list_favorites)).check(matches(hasChildCount(0)));
    }

    /**after checking that the 'favorites' list is empty as expected when our app is launched,
     *we simulate the 'add-to-favorite' action of a neighbour: First we click on a neighbour item
     * in our Neighbours list, this action should open the detailsNeighbour of this item,
     * then we perform a click on the Favorite FAB, then we ensure that this neighbour is well displayed
     * when we swipe on the 'favorites' tab list, by clicking on the back button to come back
     * to the Neighbours list, and swiping to the 'favorites' tab list; then we simulate the reverse action:
     * removing this neighbour from 'favorite' list by clicking on the favorite FAB,
     * and checking that this neighbour is removed from 'favorite' list as expected.*/
    @Test
    public void favorite_FAB_add_and_remove_to_favorites_list() {

        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(11, click()));
        onView(withId(R.id.detailsNeighbour)).check(matches(isDisplayed()));
        onView(withId(R.id.favoriteFab)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        onView(withId(R.id.main_content)).perform(swipeLeft());
        onView(withId(R.id.list_favorites)).check(matches(isDisplayed()));
        onView(withId(R.id.list_favorites)).check(matches(hasChildCount(1)));
        onView(withId(R.id.list_favorites))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.detailsNeighbour)).check(matches(isDisplayed()));
        onView(withId(R.id.favoriteFab)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.list_favorites)).check(matches(isDisplayed()));
        onView(withId(R.id.list_favorites)).check(matches(hasChildCount(0)));

    }
}

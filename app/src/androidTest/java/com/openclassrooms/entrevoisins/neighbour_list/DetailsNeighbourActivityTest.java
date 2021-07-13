package com.openclassrooms.entrevoisins.neighbour_list;

import android.icu.text.Transliterator;

import org.hamcrest.core.AllOf;
import org.junit.Before;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import com.openclassrooms.entrevoisins.di.DI;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import butterknife.OnItemClick;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

public class DetailsNeighbourActivityTest {

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

    /** We check that clicking on item in recyclerview list launch DetailsNeighbourActivity
     * and display it*/
    @Test
    public void navigateToDetailActivity() {

        //'My Neighbours' list should be displayed.
        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        //we click on the item at the 12th position in our Neighbours list (recyclerView)
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(11, click()));
        //after clicking on this item; the DetailsNeighbourActivity should be launched.
        //So we can ensure this by checking that 'detailsNeighbour' layout is displayed, as expected.
        onView(withId(R.id.detailsNeighbour)).check(matches(isDisplayed()));
        //Finally, we ensure that the datas displayed by the DetailsNeighbourActivity are correct,
        //by comparing the 'prenomInfos' textView content who should matches with "Ludovic",
        //who is the Neighbour's name we find at the 12th position in our DUMMY_NEIGHBOURS list.
        onView(withId(R.id.prenomInfos)).check(matches(withText("Ludovic")));

    }
}
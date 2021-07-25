package com.openclassrooms.entrevoisins.utils;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import android.view.View;
import com.openclassrooms.entrevoisins.R;
import org.hamcrest.Matcher;

public class ClickViewAction implements ViewAction {

    public Matcher<View> getConstraints() { return null; }

    @Override
    public String getDescription() {
        return "Click on specific item";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View item = view.findViewById(R.id.list_neighbours);
        // Maybe check for null
        item.performClick();
    }
}

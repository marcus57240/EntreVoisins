package com.openclassrooms.entrevoisins;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourPagerAdapter;

import butterknife.ButterKnife;

public class DetailsNeighbourActivity extends Activity {

    ImageButton backButton;
    Button favoriteFab;
    ImageView detailsPhotoFragment;
    TextView prenomInfos;
    TextView detailsPhotoName;
    TextView adressInfo;
    TextView phoneInfo;
    TextView webInfo;
    TextView aboutMeDescription;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);

        backButton = (ImageButton)findViewById(R.id.backButton);
        favoriteFab = (Button)findViewById(R.id.favoriteFab);
        detailsPhotoFragment = (ImageView)findViewById(R.id.detailsPhotoFragment);
        prenomInfos = (TextView)findViewById(R.id.prenomInfos);
        detailsPhotoName = (TextView)findViewById(R.id.detailsPhotoName);
        adressInfo = (TextView)findViewById(R.id.adressInfo);
        phoneInfo = (TextView) findViewById(R.id.phoneInfo);
        webInfo = (TextView) findViewById(R.id.webInfo);
        aboutMeDescription = (TextView) findViewById(R.id.aboutMeDescription);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        favoriteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}

package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.ButterKnife;

public class DetailsNeighbourActivity extends Activity {

    @BindView(R.id.detailsPhotoFragment)
    ImageView mDetailPhotoFragment;
    @BindView(R.id.detailsPhotoName)
    TextView mDetailPhotoName;
    @BindView(R.id.prenomInfos)
    TextView mPrenomInfos;
    @BindView(R.id.adressInfo)
    TextView mAdressInfo;
    @BindView(R.id.phoneInfo)
    TextView mPhoneInfo;
    @BindView(R.id.webInfo)
    TextView mWebInfo;
    @BindView(R.id.aboutMeDescription)
    TextView mAboutMeDescription;
    @BindView(R.id.backButton)
    ImageButton mBackButton;
    @BindView(R.id.favoriteFab)
    FloatingActionButton mFavoriteFab;

    private Neighbour neighbour;
    private NeighbourApiService mApiService;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);
        ButterKnife.bind(this);

        mApiService = DI.getNeighbourApiService();

        initNeighbour();

        initListener();
    }

    private void initNeighbour() {
        if(getIntent().hasExtra("selected_item")) {
            neighbour = getIntent().getParcelableExtra("selected_item");
            mDetailPhotoName.setText(neighbour.getName());
            mPhoneInfo.setText(neighbour.getPhoneNumber());
            mPrenomInfos.setText(neighbour.getName());
            mAdressInfo.setText(neighbour.getAddress());
            mWebInfo.setText(neighbour.getWebSite());
            mAboutMeDescription.setText(neighbour.getAboutMe());
            Glide
                    .with(getApplicationContext())
                    .load(neighbour.getAvatarUrl())
                    .into(mDetailPhotoFragment);

            setFabColor(!neighbour.isFavorite());
        }
    }

    private void initListener() {
        mFavoriteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!neighbour.isFavorite()) {
                    setFabColor(false);
                    neighbour.setFavorite(true);
                    mApiService.addFavoriteNeighbour(neighbour);
                    Toast.makeText(getApplicationContext(), getString(R.string.added_label), Toast.LENGTH_SHORT).show();
                } else {
                    setFabColor(true);
                    neighbour.setFavorite(false);
                    mApiService.deleteFavoriteNeighbour(neighbour);
                    Toast.makeText(getApplicationContext(), getString(R.string.removed_label), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setFabColor(boolean isFavorite) {
        int drawable = (isFavorite) ? getResources().getColor(R.color.colorPrimary) : Color.YELLOW;
        mFavoriteFab.setBackgroundTintList(ColorStateList.valueOf(drawable));
//        if (isFavorite) {
//            mFavoriteFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary) ));
//        } else {
//            mFavoriteFab.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
//        }
    }
}

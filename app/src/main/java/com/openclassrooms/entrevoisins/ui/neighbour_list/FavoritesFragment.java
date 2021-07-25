package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;


public class FavoritesFragment extends Fragment implements MyNeighbourRecyclerViewAdapter.OnItemClickListener {

    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter mMyNeighbourRecyclerViewAdapter;

    /**
     * Create and return a new instance
     * @return @{@link FavoritesFragment}
     */
    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }
    /**
     * Init the List of neighbours
     */
    private void initList() {
        mMyNeighbourRecyclerViewAdapter = new MyNeighbourRecyclerViewAdapter(mApiService.getFavoritesNeighbours(),this);
        mRecyclerView.setAdapter(mMyNeighbourRecyclerViewAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteFavoriteNeighbour(DeleteFavoriteEvent event) {
        mApiService.deleteFavoriteNeighbour(event.neighbour);
        initList();
    }

    /**method used to launch the DetailsNeighbourActivity page, when a neighbour item
     * is clicked in the list.
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), DetailsNeighbourActivity.class);
        intent.putExtra("selected_item", mApiService.getFavoritesNeighbours().get(position));
        startActivity(intent);

    }
}

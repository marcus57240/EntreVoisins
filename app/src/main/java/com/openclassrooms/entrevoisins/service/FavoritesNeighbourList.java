package com.openclassrooms.entrevoisins.service;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FavoritesNeighbourList {

    public static List<Neighbour> FAVORITES_NEIGHBOURS = Arrays.asList();

    static List<Neighbour> generateFavoritesNeighbours() {
        return new ArrayList<>(FAVORITES_NEIGHBOURS);
    }
}

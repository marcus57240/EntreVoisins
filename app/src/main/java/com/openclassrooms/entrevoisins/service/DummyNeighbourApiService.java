package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    public List<Neighbour> getFavoritesNeighbours() {
        List<Neighbour> favorites = new ArrayList<>();

        for(int i =0; i < neighbours.size(); i++) {
            Neighbour neighbour = neighbours.get(i);
            if (neighbour.isFavorite()) {
                favorites.add(neighbour);
            }
        }
        return favorites;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        int position = neighbours.indexOf(neighbour);
        neighbours.get(position).setFavorite(false);
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        int position = neighbours.indexOf(neighbour);
        neighbours.get(position).setFavorite(true);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }
}
